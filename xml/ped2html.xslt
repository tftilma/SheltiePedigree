<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet 
	version="1.0" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output 
	method="html" 
	encoding="iso-8859-1"/>
<!-- xsl:output 
    method="html"  
    encoding="iso-8859-1"
    omit-xml-declaration="yes"
    doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
    doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
    standalone="no"
    indent="yes"
    / -->
<xsl:template match="pedigree">
    <xsl:apply-templates select="animal"/>
</xsl:template>

<xsl:template match="animal">
arrId[<xsl:value-of select="idx"/>]="<xsl:value-of select='id'/>";
arrName[<xsl:value-of select="idx"/>]="<xsl:value-of select='name'/>";
</xsl:template>
    
<xsl:template match="childNode">
<xsl:apply-templates select='child'/>
</xsl:template>

<xsl:template match="child">
var kn="<xsl:value-of select='kennelname'/>";
var kn2="<xsl:value-of select='kennelname2'/>";
if (kn==".") {kn = "No prefix or affix";}
if (kn2==".") {kn2 = "No prefix or affix";}
var kennelId="<xsl:value-of select='kennelid'/>";
var kennelId2="<xsl:value-of select='kennelid2'/>";
var childName="<xsl:value-of select='name'/>";
var gender="<xsl:value-of select='gender'/>";
var regyear="<xsl:value-of select='regyear'/>";
var regmonth="<xsl:value-of select='regmonth'/>";
var childId="<xsl:value-of select='childid'/>";
var born="<xsl:value-of select='born'/>";
var color="<xsl:value-of select='color'/>";
var prevengch="<xsl:value-of select='prevengch'/>";
var nextengch= "<xsl:value-of select='nextengch'/>";
var prevnlch="<xsl:value-of select='prevnlch'/>";
var nextnlch="<xsl:value-of select='nextnlch'/>";
var allTitles ="";
var title ="";
var chYear = "";
var line="<xsl:value-of select='line'/>";
var family="<xsl:value-of select='family'/>";
var exportfrom="<xsl:value-of select='exportfrom'/>";
var importto="<xsl:value-of select='importto'/>";
<xsl:apply-templates select='chs'/>
</xsl:template>

<xsl:template match="ch">
title="<xsl:value-of select='chtitle'/>";
chYear="<xsl:value-of select='chyear'/>";
if (title=="Eng" || title=="NL") {title=title+"("+chYear+")";}
allTitles=allTitles+title+' Ch ';
</xsl:template>

<xsl:template match="chs">
<xsl:apply-templates select='ch'/>
</xsl:template>

<xsl:template match="descender">
nr="1";
nr="<xsl:value-of select='nr'/>";
descId="<xsl:value-of select='descid'/>";
descName="<xsl:value-of select='descname'/>";
gc="<xsl:value-of select='gender'/>";
fi="<xsl:value-of select='fatherid'/>";
fn="<xsl:value-of select='fathername'/>";
mi="<xsl:value-of select='motherid'/>";
mn="<xsl:value-of select='mothername'/>";
nc="<xsl:value-of select='nchildren'/>";
w('<tr>');
w('<td>'+nr+'</td><td><a href="'+descId+'.html">'+descName+'</a></td>');
w('<td>'+gc+'</td>');
if (fi!="?") {w('<td><a href="'+fi+'.html">'+fn+'</a></td>');}
else {w('<td>?</td>');}
if (mi!="?") {w('<td><a href="'+mi+'.html">'+mn+'</a></td>');}
else {w('<td>?</td>');}
w('<td>'+nc+'</td>');
w('</tr>');
</xsl:template>



<xsl:template match="descendants">
<table border='1' align="center">
<tr><th></th><th>Child</th><th>Gender</th><th>Father</th><th>Mother</th>
    <th>Number of Childs<br/>Offspring</th>
</tr>
<script language="JavaScript">
var nr="0";
var descId="id";
var descName;
var gc="";
var fi="";
var fn="";
var mi="";
var mn="";
var nc="0";
<xsl:apply-templates select="descender"/>
var xcv="2";
</script>
</table>
</xsl:template>



<xsl:template match="root">
<html><head>
<meta name="description" content="Homepage of Portma Zathe Shetland Sheepdogs and Shetland Ponys"/>
<meta name="keywords" content="Portma Zathe, Sheltie, Shetland Sheepdog, Pedigree"/>
<title><xsl:value-of select='childNode/child/name'/></title>
<script language="JavaScript" src="../js/navigate.js"/>
<script language="JavaScript" src="../js/pedigree.js"/>
<script language="JavaScript">
<xsl:apply-templates select="childNode"/>
</script>
<link rel="stylesheet" type="text/css" href="../css/stylesheet.css"/>
</head>
<body background="../img/_tile2.jpg" bgcolor="#113045" link="#00CEF4" vlink="#00CEF4" alink="#00CEF4">
<div class="edge1">
<div class="edge2">
<h1><xsl:value-of select='childNode/child/name'/></h1>
<table align="center">
<tr valign="TOP">
<script language="JavaScript">
w('<td>');
var photo=<xsl:value-of select='childNode/child/containsphoto'/>;
if (photo==true) w('<img border="no" src="../photo/'+childId+'.jpg"/>');
w('</td><td>');
w('<table>');
w('<tr>');
w('<td>Born</td><td>'+born+'</td>');
w('</tr>');
w('<tr>');
w('<td>Line and Family</td><td>'+line+' '+family+'</td>');
 w('</tr>');
w('<tr>');
w('<td>Colour</td><td>'+col(color)+'</td>');
w('</tr>');
w('<tr>');
w('<td>Gender</td><td>'+gender2str(gender)+'</td>');
w('</tr>');
if (regyear!=""){
w('<tr>');
w('<td>Registration</td><td>'+regyear+'-'+regmonth+'</td>');
w('</tr>');
} else {
w('<tr>');
w('<td></td><td></td>');
w('</tr>');
}
if (exportfrom!="") {
  w('<tr>');
  w('<td>Export from</td><td>'+exportfrom+'</td>');
  w('</tr>');
}
if (importto!="") {
  w('<tr>');
  w('<td>Import to</td><td>'+importto+'</td>');
  w('</tr>');
}
w('<tr>');
w('<td colspan="2">');
w(allTitles);
w('</td>');
w('</tr>');
w('<tr>');
if (prevengch!="") w('<td><a href="'+prevengch+'.html">Prev Ch</a></td>');
if (nextengch!="") w('<td><a href="'+nextengch+'.html">Next Ch</a></td>');
if (prevnlch!="") w('<td><a href="'+prevnlch+'.html">Prev Ch</a></td>');
if (nextnlch!="") w('<td><a href="' + nextnlch +'.html">Next Ch</a></td>');
w('</tr>');
w('</table>');
w('</td>');
</script>
</tr>
</table>

<br/>
<img border="no" src="../img/_plus.gif"/>
<img border="no" src="../img/_smallbar.jpg"/>
<img border="no" src="../img/_plus.gif"/>
<table border="10" cellpadding="0" cellspacing="0" width="100%">
<tbody>
<tr>
<script language="JavaScript">
var arrName = new Array(64);
var arrId = new Array(64);
<xsl:apply-templates select="pedigree"/>
ShowStamboom(5, arrName, arrId);
</script>
</tr>
</tbody>
</table>
<br/>
<img border="no" src="../img/_plus.gif"/>
<img border="no" src="../img/_smallbar.jpg"/>
<img border="no" src="../img/_plus.gif"/>
<h3>Offsprings</h3>
<xsl:apply-templates select="descendants"/>
<img border="no" src="../img/_longbar.jpg"/>
<h3>Kennels</h3>
<script language="JavaScript">
w('<a href="'+kennelId+'.html">'+kn+'</a>');
if (kennelId2 != "null"){
w('<br/>');
w('<a href="'+ kennelId2+'.html">'+kn2+'</a>');}
abc();
</script>
</div>
</div>
<script language="JavaScript">
navigate();
</script>
</body></html>
</xsl:template>
</xsl:stylesheet>
