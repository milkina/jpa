<meta property="og:title" content="<c:out value="${titleName}"/>"/>
<meta property="og:type" content = "article" />
<meta property="og:description" content = "${CATEGORY_ATTRIBUTE.article.description}" />
<meta property="og:site_name" content="ExamClouds">

<meta property="twitter:title" content="<c:out value="${titleName}"/>"/>
<meta property="twitter:card" content="summary"/>
<c:choose>
 <c:when test="${CATEGORY_ATTRIBUTE.article.image != null &&  not empty CATEGORY_ATTRIBUTE.article.image}">
    <meta property="og:image" content="${CATEGORY_ATTRIBUTE.article.image}"/>
    <meta property="twitter:image" content="${CATEGORY_ATTRIBUTE.article.image}"/>
 </c:when>
 <c:otherwise>
   <meta property="og:image" content="/images/general/logo.webp"/>
   <meta property="twitter:image" content="https://www.examclouds.com/images/general/logo.webp"/>
 </c:otherwise>
</c:choose>
<meta property="twitter:description" content="${CATEGORY_ATTRIBUTE.article.description}"/>
<meta property="twitter:site" content="@ExamClouds">
