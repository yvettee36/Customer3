<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="itcast" uri="/itcast" %>
<html>
<head>
    <title>列出所有客户</title>
    <style type="text/css">
        .even {
            background-color: burlywood;
        }

        .odd {
            background-color: aliceblue;
        }
    </style>
</head>
<body style="text-align: center">
<table frame="border" width="85%" align="center">
    <tr>
        <td>客户姓名</td>
        <td>性别</td>
        <td>生日</td>
        <td>手机</td>
        <td>邮箱</td>
        <td>爱好</td>

        <td>类型</td>
        <td>备注</td>
        <td>操作</td>
    <tr>

        <c:forEach var="c" items="${requestScope.pageBean.list}" varStatus="status">
    <tr class="${status.count%2==0?'even':'odd'}">
        <td>${c.name }</td>
        <td>${c.gender }</td>
        <td>${c.birthday }</td>
        <td>${c.cellphone }</td>
        <td>${c.email }</td>
        <td>${c.preference }</td>
        <td>${c.type }</td>
        <td>${itcast:sub(c.description)}</td>
        <td>
            <a href="${pageContext.request.contextPath}/servlet/UpdateCustomerServlet?id=${c.id}">修改</a>
            <a href="javascript:;" onclick="del('${c.id}')">删除</a>
        </td>
    <tr>
        </c:forEach>
</table>
<br>
<script type="text/javascript">
    function del(id) {
        if (window.confirm("您确定删除吗？")) {
            location.href = "${pageContext.request.contextPath}/servlet/DeleteCustomerServlet?id=" + id;
        }
    }
    function gotoPage(currentPage) {
        if (currentPage < 1 || currentPage != parseInt(currentPage) || currentPage >${pageBean.totalPage}) {
            alert("请输入有效值");
            document.getElementById("pageNo").value = "";
        } else {
            var pageSize = document.getElementById("pageFit").value;
            window.location.href = '${pageContext.request.contextPath}/servlet/ListCustomerServlet?currentPage=' + currentPage + '&pageSize=' + pageSize;
        }
    }

    function changeSize(pageSize, oldValue) {
        if (pageSize < 0 || pageSize != parseInt(pageSize)) {
            alert("请输入合法值");
            document.getElementById("pageFit").value = oldValue;
        }
        window.location.href = '${pageContext.request.contextPath}/servlet/ListCustomerServlet?pageSize=' + pageSize;
    }

</script>

共[${pageBean.totalRecord}]条记录，
每页<input id="pageFit" type="text" value="${pageBean.pageSize}" onchange="changeSize(this.value,${pageBean.pageSize})"
         style="width: 30px;text-align: center">条，
共[${pageBean.totalPage}]页，
当前第[${pageBean.currentPage}]页，&nbsp;&nbsp;
<a href="javascript:;" onclick="gotoPage(${pageBean.previousPage})">上一页</a>
<c:forEach var="pageNum" items="${pageBean.pageBar}">
    <%--判断显示当前页码，超链就不能被点击--%>
    <c:if test="${pageNum==pageBean.currentPage}">
        <font color="#a9a9a9">${pageNum}</font>
    </c:if>
    <c:if test="${pageNum!=pageBean.currentPage}">
        <a href="javascript:;" onclick="gotoPage(${pageNum})">${pageNum}</a>
    </c:if>
</c:forEach>
<a href="javascript:;" onclick="gotoPage(${pageBean.nextPage})">下一页</a>
<input type="text" id="pageNo" style="width: 30px">
<input type="button" value="GO" onclick="gotoPage(document.getElementById('pageNo').value)">
</body>
</html>
