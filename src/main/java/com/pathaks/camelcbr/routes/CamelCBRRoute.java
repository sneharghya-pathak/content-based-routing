package com.pathaks.camelcbr.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelCBRRoute extends RouteBuilder{

    @Override
    public void configure() throws Exception{
        from("file:{{inFolder}}")
        .choice()
            .when(simple("${header.CamelFileName} regex '^.*[tT][xX][tT]$'"))
                .to("file:{{txtFolder}}")
            .when(simple("${header.CamelFileName} regex '^.*[xX][mM][lL]$'"))
                .to("file:{{xmlFolder}}")
            .when(simple("${header.CamelFileName} regex '^.*[dD][aA][tT]$'"))
                .to("file:{{datFolder}}")
            .otherwise()
                .to("file:{{othersFolder}}");
    }

}