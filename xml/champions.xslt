<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet 
	version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html"  encoding="iso-8859-1"/>

<xsl:template match="animals">
var id="?"; 
var name="?"; 
var born = "";
var position = "";
var chyear = "";
var gender = "";
var color = "";
var country = "NL";
var line="";
var fam="";    
country   = "<xsl:value-of select='country'/>";
if (country == "NL")
{country = "Dutch";}
else if (country == "Eng")
{country = "English";}
w('<h2>' + country + ' Shetland Sheepdog Champions</h2>');
w('<table>');
w('<tr>');
w('<th>Champion Year</th><th>Name</th><th></th>');
w('<th>Gender</th><th>Date of Birth</th><th>Line</th><th>Fam</th>');
w('<th>Colour</th>');
w('</tr>');
<xsl:apply-templates select="animal"/>
w('</table>');
</xsl:template>

<xsl:template match="animal">
position="<xsl:value-of select='position'/>";
id="<xsl:value-of select='animalid'/>";
name="<xsl:value-of select='animaltotalname'/>";
born="<xsl:value-of select='born'/>";
chyear="<xsl:value-of select='chyear'/>";
gender="<xsl:value-of select='gender'/>";
color="<xsl:value-of select='color'/>";
line="<xsl:value-of select='line'/>";
fam="<xsl:value-of select='fam'/>";
w('<tr>');
w('<td>' + position +' '+chyear+'</td>');
w('<td><a href="'+id +'.html">'+name+' </a>');
w('</td><td>');
var photo=<xsl:value-of select='containsphoto'/>;
if (photo==true) {
  var url = "../photo/" + id + ".jpg";
  var options = "width=300,height=300,statusbar=no";
  w('<a href="'+url+'" onclick="window.open(\'' +url+'\',\''+ name +'\', \''+options+'\'); return false"><img src="../img/photo.gif" border="no"/></a>');
}
    
w('</td><td>' + gender+ '</td>');
w('<td>'+born+'</td>');
w('<td>'+line+'</td>');
w('<td>'+fam+'</td>');
w('<td>'+col(color)+'</td>');
w('</tr>');
</xsl:template>

<xsl:template match="/">  
<html>
    <head>
	    <meta name="description"
	        content="Homepage of Portma Zathe Shetland Sheepdogs and Shetland Ponys"/>
	    <meta name="keywords"
	        content="Portma Zathe, Sheltie, Shetland Sheepdog, pedigree"/>
	    <title>Sheltie Champions</title>
	    <script language="JavaScript" src="../js/navigate.js"></script>
	    <!-- No underline -->
	    <style type="text/css">A { text-decoration:underline }</style>
	    <link rel="stylesheet" type="text/css" href="../css/stylesheet.css"></link>
    </head>
    
    <body background="../img/_tile2.jpg" bgcolor="#113045" link="#00CEF4"
        vlink="#00CEF4" alink="#00CEF4">
<div class="edge1">
<div class="edge2">
    
<center>
<script language="JavaScript"><xsl:apply-templates select="animals"/></script>
<br/>
<img border="no" src="../img/_longbar.jpg"/>
<h3>Alphabet of Kennels</h3>
<script language="JavaScript">abc();</script>
<br/>
</center>
    
</div>
</div>
<script language='JavaScript' >navigate();</script>
</body>
</html>
</xsl:template>
</xsl:stylesheet>
