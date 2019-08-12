package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Service}.dto.${entity}DTO;
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, EntityMapper, ${entity}DTO, ${entity}>(), ${table.serviceName}<${entity}DTO, ${entity}> {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, EntityMapper, ${entity}DTO, ${entity}> implements ${table.serviceName}<${entity}DTO, ${entity}> {

}
</#if>
