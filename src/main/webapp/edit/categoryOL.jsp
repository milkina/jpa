<meta property="og:title" content="<c:out value="${titleName}"/>"/>
<meta property="og:type" content = "article" />
<meta property="og:description" content = "${CATEGORY_ATTRIBUTE.article.description}" />
<meta property="og:url" content="https://www.examclouds.com">
<meta property="og:site_name" content="ExamClouds">

<meta property="twitter:title" content="<c:out value="${titleName}"/>"/>
<meta property="twitter:card" content="article"/>
<c:if test="${CATEGORY_ATTRIBUTE.article.image != null &&  not empty CATEGORY_ATTRIBUTE.article.image}">
    <meta property="og:image" content="${CATEGORY_ATTRIBUTE.article.image}"/>
    <meta property="twitter:image" content="${CATEGORY_ATTRIBUTE.article.image}"/>
</c:if>
<meta property="twitter:description" content="${CATEGORY_ATTRIBUTE.article.description}"/>
