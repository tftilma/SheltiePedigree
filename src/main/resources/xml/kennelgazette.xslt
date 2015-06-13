<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet 
	version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output 
    method="html"  
    encoding="iso-8859-1"
    omit-xml-declaration="yes"
    doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
    doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
    standalone="no"
    indent="yes"
    />

<xsl:template match="root">
var position=0;
var year=0;
var month="?";
var total="?";
var mytotal="?";
var id="?";
var name="?";
var gender="?";
var born="?";
var line="?";
var fam="?";
var fatherid="?";
var fathername="?";
var motherid="?";
var mothername="?";
year=<xsl:value-of select='year'/>;
showHeader(year);
<xsl:apply-templates select="animals"/>
</xsl:template>
    
<xsl:template match="animals">    
month="<xsl:value-of select='month'/>";
total="<xsl:value-of select='total'/>";
mytotal="<xsl:value-of select='mytotal'/>";
w('<h2>' + month + ' '+ year +' (' + mytotal + ' out of ' + total + ')</h2>');
w('<table>');
w('<tr>');
w('<th></th>');
w('<th>Animal</th>');
w('<th>Gender</th>');
w('<th>Born</th>');
w('<th>Line</th>');
w('<th>Fam</th>');
w('<th>Father</th>');
w('<th>Mother</th>');
w('</tr>');
<xsl:apply-templates select="animal"/>
w('</table>');
</xsl:template>

<xsl:template match="animal">
position="<xsl:value-of select='position'/>";
id="<xsl:value-of select='animalid'/>";
name="<xsl:value-of select='animaltotalname'/>";
gender="<xsl:value-of select='gender'/>";
born="<xsl:value-of select='born'/>";
line="<xsl:value-of select='line'/>";
fam="<xsl:value-of select='fam'/>";
fatherid="<xsl:value-of select='fatherid'/>";
fathername="<xsl:value-of select='fathername'/>";
motherid="<xsl:value-of select='motherid'/>";
mothername="<xsl:value-of select='mothername'/>";
w('<tr>');
w('<td>' + position + '</td>');
w('<td>');
w('<a href="../html/' + id + '.html">'+name+'</a>');
w('</td>');
w('<td>' + gender + '</td>');
w('<td>' + born + '</td>');
w('<td>'+line+'</td>');
w('<td>'+fam+'</td>');
w('<td>');
w('<a href="../html/' + fatherid + '.html">'+fathername+'</a>');
w('</td>');
w('<td>');
w('<a href="../html/' + motherid + '.html">'+mothername+'</a>');
w('</td>');
w('</tr>');
</xsl:template>
        
<xsl:template match="/">   
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
        <meta name="description"
            content="Homepage of Portma Zathe Shetland Sheepdogs and Shetland Ponys"/>
        <meta name="keywords"
            content="Portma Zathe, Sheltie, Shetland Sheepdog, pedigree"/>
        <title>Registrations for Shetland Sheepdogs</title>
        <script language="JavaScript" src="../js/navigate.js"
            type="text/javascript">var leeg=1;
        </script>
            
        <script language="JavaScript" src="../js/kg.js" type="text/javascript">
            var leeg2=1;
        </script>
        <link rel="stylesheet" type="text/css" href="../css/kg.css"></link>
    </head>
    
    <body>
<div class="edge1">
<div class="edge2">
    
<center>
<script language="JavaScript" type="text/javascript">
    <xsl:apply-templates select="root"/>
</script>
<br/>
<img border="no" src="../img/_longbar.jpg"/>
<h3>Alphabet of Kennels</h3>
<script language="JavaScript" type="text/javascript">
    abc();
</script>
<br/>
</center>
    
</div>
</div>
<script language='JavaScript'  type="text/javascript">
    navigate();
</script>
    </body>
</html>
</xsl:template>
</xsl:stylesheet>
