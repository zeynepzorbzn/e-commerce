package com.zeynep.eTicaretSitesi.core.exceptions;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.stereotype.Controller;

@Controller
public class GlobalExceptionHandler {

    @GraphQlExceptionHandler(UserAlreadyExistsException.class)
    public GraphQLError handle(UserAlreadyExistsException ex, DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError(env).message(ex.getMessage()).build();
    }

    @GraphQlExceptionHandler(UserNotFoundException.class)
    public GraphQLError handle(UserNotFoundException ex, DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError(env).message(ex.getMessage()).build();
    }

    @GraphQlExceptionHandler(RoleNotFoundException.class)
    public GraphQLError handle(RoleNotFoundException ex, DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError(env).message(ex.getMessage()).build();
    }

}