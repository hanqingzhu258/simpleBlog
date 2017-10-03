<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8"  isELIgnored="false"%>
<c:if test="${requestScope.msgType eq 'SUCCESS'}">
    <c:set var="msgType" value="success"/>
</c:if>
<c:if test="${requestScope.msgType eq 'WARNING'}">
    <c:set var="msgType" value="warning"/>
</c:if>
<c:if test="${requestScope.msgType eq 'ERROR'}">
    <c:set var="msgType" value="danger"/>
</c:if>
<c:if test="${requestScope.msg !=null}">
    <div class="alert alert-${msgType} alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span
                class="sr-only">Close</span></button>
            ${requestScope.msg}
    </div>
</c:if>