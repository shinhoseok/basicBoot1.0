package com.basic.pagination;

import java.util.Collections;
import java.util.Set;

import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

public class BasicPaginationDialect extends AbstractDialect implements IExpressionObjectDialect {

    public BasicPaginationDialect() {
        super("BasicPaginationDialect");
    }

    @Override
    public IExpressionObjectFactory getExpressionObjectFactory() {
        return new IExpressionObjectFactory() {
            @Override
            public Set<String> getAllExpressionObjectNames() {
                return Collections.singleton("basicPaginationFormat");
            }
            @Override
            public Object buildObject(IExpressionContext context, String expressionObjectName) {
                return new BasicPaginaionFormat();
            }
            @Override
            public boolean isCacheable(String expressionObjectName) {
                return true;
            }
        };
    }

}