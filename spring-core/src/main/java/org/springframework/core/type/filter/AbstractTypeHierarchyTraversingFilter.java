/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.core.type.filter;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.lang.Nullable;

/**
 * 知道遍历层次结构的类型过滤器。
 * 当需要基于潜在的整个类接口层次结构进行匹配时，此过滤器很有用。
 * 所采用的算法使用快速成功策略：如果在任何时候声明匹配，则不执行进一步处理。
 * <p>
 * Type filter that is aware of traversing over hierarchy.
 *
 * <p>This filter is useful when matching needs to be made based on potentially the
 * whole class/interface hierarchy. The algorithm employed uses a succeed-fast
 * strategy: if at any time a match is declared, no further processing is
 * carried out.
 *
 * @author Ramnivas Laddad
 * @author Mark Fisher
 * @since 2.5
 */
public abstract class AbstractTypeHierarchyTraversingFilter implements TypeFilter {

	protected final Log logger = LogFactory.getLog(getClass());

	private final boolean considerInherited;

	private final boolean considerInterfaces;


	protected AbstractTypeHierarchyTraversingFilter(boolean considerInherited, boolean considerInterfaces) {
		this.considerInherited = considerInherited;
		this.considerInterfaces = considerInterfaces;
	}


	@Override
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
			throws IOException {

		// This method optimizes avoiding unnecessary creation of ClassReaders
		// as well as visiting over those readers.
		// 此方法优化了避免创建不必要的 ClassReader 以及访问这些读取器。
		/**
		 * 这里的excludeFilters中之前构造了一个AbstractTypeHierarchyTraversingFilter，
		 * 默认添加了一个@ComponentScan的类，其实就是默认把AppConfig排除在外了，
		 * 另外AbstractTypeHierarchyTraversingFilter这个过滤器其实
		 * considerInherited和considerInterfaces都为false
		 * 所以整个的这个match方法会返回false
		 */
		// AbstractTypeHierarchyTraversingFilter中该方法的实现默认返回false
		if (matchSelf(metadataReader)) {
			return true;
		}
		ClassMetadata metadata = metadataReader.getClassMetadata();
		// 这里默认添加的AbstractTypeHierarchyTraversingFilter重写了该方法，与appConfig名字相同的才匹配
		if (matchClassName(metadata.getClassName())) {
			return true;
		}

		if (this.considerInherited) {
			String superClassName = metadata.getSuperClassName();
			if (superClassName != null) {
				// Optimization to avoid creating ClassReader for super class.
				Boolean superClassMatch = matchSuperClass(superClassName);
				if (superClassMatch != null) {
					if (superClassMatch.booleanValue()) {
						return true;
					}
				} else {
					// Need to read super class to determine a match...
					try {
						if (match(metadata.getSuperClassName(), metadataReaderFactory)) {
							return true;
						}
					} catch (IOException ex) {
						if (logger.isDebugEnabled()) {
							logger.debug("Could not read super class [" + metadata.getSuperClassName() +
									"] of type-filtered class [" + metadata.getClassName() + "]");
						}
					}
				}
			}
		}

		if (this.considerInterfaces) {
			for (String ifc : metadata.getInterfaceNames()) {
				// Optimization to avoid creating ClassReader for super class
				Boolean interfaceMatch = matchInterface(ifc);
				if (interfaceMatch != null) {
					if (interfaceMatch.booleanValue()) {
						return true;
					}
				} else {
					// Need to read interface to determine a match...
					try {
						if (match(ifc, metadataReaderFactory)) {
							return true;
						}
					} catch (IOException ex) {
						if (logger.isDebugEnabled()) {
							logger.debug("Could not read interface [" + ifc + "] for type-filtered class [" +
									metadata.getClassName() + "]");
						}
					}
				}
			}
		}

		return false;
	}

	private boolean match(String className, MetadataReaderFactory metadataReaderFactory) throws IOException {
		return match(metadataReaderFactory.getMetadataReader(className), metadataReaderFactory);
	}

	/**
	 * 覆盖它以单独匹配自我特征。通常，实现将使用访问者提取信息来执行匹配。
	 * Override this to match self characteristics alone. Typically,
	 * the implementation will use a visitor to extract information
	 * to perform matching.
	 */
	protected boolean matchSelf(MetadataReader metadataReader) {
		return false;
	}

	/**
	 * 覆盖它以匹配类型名称。
	 * Override this to match on type name.
	 */
	protected boolean matchClassName(String className) {
		return false;
	}

	/**
	 * Override this to match on super type name.
	 */
	@Nullable
	protected Boolean matchSuperClass(String superClassName) {
		return null;
	}

	/**
	 * Override this to match on interface type name.
	 */
	@Nullable
	protected Boolean matchInterface(String interfaceName) {
		return null;
	}

}
