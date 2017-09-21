<nav>
<table class="table-striped">
    <thead><tr><td>Topic</td><!--<td>No. of Questions</td>--></tr></thead>
    <%
       for (Category category : categories) {%>
           <tr><td><a href="<%=contextPath%>/java/<%=test.getPathName()%>/<%=category.getPathName()%>"><%=category.getName()%>
                   </a></td>
                <!--<td>67 Questions</td>-->
            </tr>
    <%}%>
</table>
</nav>