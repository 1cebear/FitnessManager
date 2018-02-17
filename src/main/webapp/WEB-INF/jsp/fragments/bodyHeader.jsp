<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <sec:authorize access="!hasRole('ROLE_ADMIN')">
            <a href="/main" class="navbar-brand">Main</a>
        </sec:authorize>
        <sec:authorize access="!hasRole('ROLE_ADMIN')">
            <a href="/parameters" class="navbar-brand">Parameters</a>
        </sec:authorize>
        <div class="collapse navbar-collapse">
            <form class="navbar-form navbar-right">

                <a class="btn btn-primary" href="logout">
                    <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                </a>
            </form>
        </div>
    </div>
</div>