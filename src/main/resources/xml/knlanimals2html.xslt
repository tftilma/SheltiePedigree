<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" encoding="iso-8859-1"/>
<xsl:template match="kennel">
<script language="JavaScript">
var id="?";
var name="?";
var nickName="";
var gender="";
var born="";
var position="";
var kennelId="<xsl:value-of select='kennelid'/>";
var kennelName="<xsl:value-of select='kennelname'/>";
var nChildren="";
var manyChildren="0";
var line="";
var fam="";
if (kennelName=="" || kennelName==".")
{ kennelName = "No prefix or affix"; }
w('<title>'+kennelName+'</title>');
</script>
</xsl:template>
<xsl:template match="animals">
<xsl:apply-templates select="animal"/>
</xsl:template>
<xsl:template match="animal">
id="<xsl:value-of select='animalid'/>";
name="<xsl:value-of select='animaltotalname'/>";
nickName="<xsl:value-of select='nickname'/>";
gender="<xsl:value-of select='gender'/>";
born="<xsl:value-of select='born'/>";
position="<xsl:value-of select='position()'/>";
nChildren = "<xsl:value-of select='nchildren'/>";
line = "<xsl:value-of select='line'/>";
fam = "<xsl:value-of select='fam'/>";
manyChildren="<xsl:value-of select='manychildren'/>";
w('<tr>');
w('<td>');
bm(manyChildren == "1", position);
w('</td>');
w('<td>');
w('<a href="'+id+'.html">');
bm(manyChildren == "1", name);
w('</a></td><td>');
var photo=<xsl:value-of select='containsphoto'/>;
if (photo==true) {
  var url = "../photo/" + id + ".jpg";
  var options = "width=300,height=300,statusbar=no";
  w('<a href="'+url+'" onclick="window.open(\'' +url+'\',\''+id+'\', \''+options+'\'); return false"><img src="../img/photo.gif" border="no"/></a>');
}
w('</td><td>');
bm(manyChildren == "1", gender);
w('</td><td>');
bm(manyChildren == "1", born);
w('</td><td>');
bm(manyChildren == "1", line);
w('</td><td>');
bm(manyChildren == "1", fam);
w('</td><td>');
bm(manyChildren == "1", nChildren);
w('</td>');    
w('</tr>');
</xsl:template>

<xsl:template match="root">
<html><head>
<meta name="description" content="Homepage of Portma Zathe Shetland Sheepdogs and Shetland Ponys"/>
<meta name="keywords" content="Portma Zathe, Sheltie, Shetland Sheepdog"/>
<script language="JavaScript" src="../js/navigate.js"></script>
<xsl:apply-templates select="kennel"/>
<link rel="stylesheet" type="text/css" href="../css/stylesheet.css"></link>
</head>
<body background="../img/_tile2.jpg" bgcolor="#113045" link="#00CEF4" vlink="#00CEF4" alink="#00CEF4">
<div class="edge1">
<div class="edge2">
<center>
<script language="JavaScript">
w("<h2>"+kennelName+"</h2>");
w("<p>Shelties found in the kennel <b>"+kennelName+"</b>:<br/>");
w("(Dogs who have produced 10 (5 for bitches) or more children (known to this program) are marked in <b>BOLD-face</b>)</p>");
w("<table>");
w("<tr>");
w('<th></th><th>Name</th><th></th><th>Gender</th>');
w('<th>Born date</th><th>Line</th><th>Family</th><th>Number of Children</th>');
w('</tr>');
<xsl:apply-templates select="animals"/>
w("</table>");
</script>
<br/>
<img border="no" src="../img/_longbar.jpg"/>
<script language="JavaScript">
abc();
</script>
</center>
</div>
</div>
<script language='JavaScript'>navigate();</script>
</body></html>
</xsl:template>
</xsl:stylesheet>
