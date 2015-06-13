<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

        
<xsl:output method="html" encoding="ISO-8859-1"/>
    
<xsl:template match="totals">
<table>
<tr>
<td>Number of Shelties</td>
<td><xsl:value-of select="nanimals"/></td>
</tr>
<tr>
<td>Number of Kennels</td>
<td><xsl:value-of select="nkennels"/></td>
</tr>
<tr>
<td>Number of Photos</td>
<td><xsl:value-of select="nphotos"/></td>
</tr>
</table>
</xsl:template>
    
<xsl:template match="percColors">
<dl>
<dt>Colours in % (and counted)</dt>        
<xsl:apply-templates select="percColor"/>
</dl>
</xsl:template>

<xsl:template match="percLines">
<dl>
<dt>Lines in % (and counted)</dt>        
<xsl:apply-templates select="percLine"/>
</dl>
</xsl:template>

<xsl:template match="percFams">
<dl>
<dt>Families in % (and counted)</dt>        
<xsl:apply-templates select="percFam"/>
</dl>
</xsl:template>

<xsl:template match="percColor">
<dd>
<script language="JavaScript">
negPerc="<xsl:value-of select='negPerc'/>";
key="<xsl:value-of select='key'/>";
count="<xsl:value-of select='count'/>";
perc = "<xsl:value-of select='perc'/>";
w('<b style="width:');
w(negPerc);
w('%"> ');
w(key);
w(' = ');
w(count);
w(' ( ');
w(perc);
w('%)</b>');
</script>
</dd>
</xsl:template>

<xsl:template match="percLine">
<dd>
<script language="JavaScript">
negPerc="<xsl:value-of select='negPerc'/>";
key="<xsl:value-of select='key'/>";
count="<xsl:value-of select='count'/>";
perc = "<xsl:value-of select='perc'/>";
w('<b style="width:');
w(negPerc);
w('%"> ');
w(key);
w(' = ');
w(perc);
w('% ( ');
w(count);
w(')</b>');
</script>
</dd>
</xsl:template>

<xsl:template match="percFam">
<dd>
<script language="JavaScript">
negPerc = "<xsl:value-of select='negPerc'/>";
key = "<xsl:value-of select='key'/>";
perc = "<xsl:value-of select='perc'/>";
count = "<xsl:value-of select='count'/>";
w('<b style="width:');
w(negPerc);
w('%"> ');
w(key);
w(' = ');
w(perc);
w('% (');
w(count);
w(')</b>');
</script>
</dd>
</xsl:template>

<!-- 
<xsl:template match="percColor">
<dd>
<script language="JavaScript">
negPerc = "<xsl:value-of select='negPerc'/>";
key = "<xsl:value-of select='key'/>";
perc = "<xsl:value-of select='perc'/>";
count = "<xsl:value-of select='count'/>";
w('<b style="width:');
w(negPerc);
w('%"> ');
w(key);
w(' = ');
w(perc);
w('% (');
w(count);
w(')</b>');
</script>
</dd>
</xsl:template>
 -->
<xsl:template match="largekennel">
kennelIdx = "<xsl:value-of select='kennelidx'/>";
kennelId = "<xsl:value-of select='kennelid'/>";
kennelname = "<xsl:value-of select='kennelname'/>";
nAnimals = "<xsl:value-of select='nanimals'/>";
w('<tr>');
w('<td>'+kennelIdx + '</td>');
w('<td><a href="'+kennelId+'.html">'+kennelname+'</a></td>');
w('<td>'+ nAnimals +'</td>');
w('</tr>');
</xsl:template>

<xsl:template match="largestkennels">
<table border="1">
<tr>
<th></th><th>Largest kennels</th><th>Shelties</th>
</tr>
<script language="JavaScript">
var kennelIdx="0";
var kennelId="?";
var kennelname="?";
var nAnimals="0";
<xsl:apply-templates select="largekennel"/>
</script>
</table>
</xsl:template>

<xsl:template match="mostoffspring">
animalIdx = "<xsl:value-of select='animalidx'/>";
animalId = "<xsl:value-of select='topperid'/>";
animalname = "<xsl:value-of select='toppername'/>";
nChildren = "<xsl:value-of select='nchildren'/>";
born = "<xsl:value-of select='born'/>";
w('<tr>');
w('<td>'+animalIdx + '</td>');
w('<td><a href="'+animalId+'.html">'+animalname+'</a></td>');
w('<td>'+ nChildren +'</td>');
w('<td>'+ born +'</td>');
w('</tr>');
</xsl:template>

<xsl:template match="mostoffsprings">
<table border="1">
<tr>
<th></th>
<th>Shelties with most produced offspring</th><th>Offspring<br/>size</th>
<th>Born</th>
</tr>
<script language="JavaScript">
var animalIdx = "0";
var animalId="?";
var animalname="?";
var nChildren="0";
var born="";
<xsl:apply-templates select="mostoffspring"/>
</script>
</table>
</xsl:template>

<xsl:template match="root">
<html>
<head>
<meta name="description" content="Pedigree page of Shetland Sheepdogs"/>
<meta name="keywords"
      content="Portma Zathe, Sheltie, Shetland Sheepdog, pedigree"/>
<script language="JavaScript" src="../js/navigate.js"/>
<title>Statistics</title>
<link rel="stylesheet" type="text/css" href="../css/stylesheet.css"/>
<link rel="stylesheet" type="text/css" href="../css/graph.css"/>
</head>

<body background="../img/_tile2.jpg" bgcolor="#113045" link="#00CEF4"
    vlink="#00CEF4" alink="#00CEF4">
<div class="edge1">
<div class="edge2">
<h1>Statistics</h1>
<h3>Click on any kennel or sheltie</h3>
<center>
<script language="JavaScript">                
var negPerc = "";
var key = "";
var perc = "";
var count = "";
</script>
<table>
<tr>
<td><xsl:apply-templates select="totals"/></td>
<td rowspan="4"><xsl:apply-templates select="percFams"/></td>
</tr>
<tr>
<td><xsl:apply-templates select="percColors"/></td>
</tr>
<tr>
<td>
    
<table>
<tr>
<th>Abrev</th><th>Meaning</th>
</tr>
<tr>
<td>sw</td><td>Sable and White</td>
</tr>
<tr>
<td>tri</td><td>Tricolour</td>
</tr>
<tr>
<td>bm</td><td>Blue Merle</td>
</tr>
<tr>
<td>bw</td><td>Black and White</td>
</tr>
<tr>
<td>bb</td><td>Bi Blue</td>
</tr>
<tr>
<td>sm</td><td>Sable Merle</td>
</tr>
<tr>
<td>dm</td><td>Double Merle</td>
</tr>
<tr>
<td>chw</td><td>Color Headed White</td>
</tr>
<tr>
<td>bt</td><td>Black and Tan</td>
</tr>
</table>
    
</td>
</tr>
<tr>
<td><xsl:apply-templates select="percLines"/></td>
</tr>
</table>
<table>
<tr>
<td></td>
</tr>
<tr>
<td></td>
</tr>
<tr>
<td><xsl:apply-templates select="largestkennels"/></td>
<td></td><td></td><td></td><td></td>
<td><xsl:apply-templates select="mostoffsprings"/></td>
</tr>
</table> 
<img border="no" src="../img/_longbar.jpg"/>
<h3>Kennels</h3>
<script language="JavaScript">               
abc();
</script>
<br/>
</center>
</div>
</div>
<script language="JavaScript">
navigate();
</script>
</body>
</html>
</xsl:template>
    
</xsl:stylesheet>
