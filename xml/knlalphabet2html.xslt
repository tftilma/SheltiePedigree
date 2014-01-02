<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" encoding="iso-8859-1"/>
<xsl:template match="kennels">
<script language='JavaScript' >
var kennelid="";
var kennelislarge="0";
</script>
<p>Kennels starting with the character
<b><xsl:value-of select='firstchar'/></b>:<br/>
(Kennels marked in <b>BOLD-face</b> contain 10 or more Shelties known to this program)
</p>
<ul><xsl:apply-templates select="kennel"/></ul>
</xsl:template>
<xsl:template match="kennel">
<li>
<script language='JavaScript' >
kennelid ="<xsl:value-of select='kennelid'/>";
kennelislarge ="<xsl:value-of select='kennelislarge'/>";
w('<a href="' + kennelid + '.html">');
bm(kennelislarge == 1, "<xsl:value-of select='kennelname'/> (<xsl:value-of select='kenneltotal'/>)");
w('</a>');
</script>
</li>
</xsl:template>

<xsl:template match="/">
<html>
<head>
<meta name="description"
content="Pedigrees of Shetland Sheepdogs"/>
<meta name="keywords"
content="Portma Zathe, Sheltie, Shetland Sheepdog"/>
<!-- meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/ -->
    
<title>Kennels with same starting character</title>
<script language="JavaScript" src="../js/navigate.js">
</script>
<!-- No underline -->
<style type="text/css">
a { text-decoration:underline }
</style>
<link rel="stylesheet" type="text/css" href="../css/stylesheet.css">
</link>
</head>
<body background="../img/_tile2.jpg" bgcolor="#113045" link="#00CEF4" vlink="#00CEF4" alink="#00CEF4">
<div class="edge1">
<div class="edge2">
<center><h2>Kennels</h2>
<xsl:apply-templates select="kennels"/>
<br/>
<img border="no" src="../img/_longbar.jpg"/>
<h3>Alphabet of Kennels</h3>
<script language="JavaScript">
abc();
</script>
<br/>
</center>
</div>
</div>
<script language='JavaScript' >
navigate();
</script>
</body>
</html>
</xsl:template>
</xsl:stylesheet>
