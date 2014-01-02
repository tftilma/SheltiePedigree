function showHeader(year) 
{
  var prevyear=0;
  var nextyear=0;
  w('<h1 xmlns="">Registrations in ' + year + '</h1>');
  if (year > 1909) {
    prevyear = year - 1;
    w('<a xmlns="" href="_' + prevyear + '.html">prev</a>');
  }
  w('  ');
  if (year < 1980) {
    nextyear = year;
    nextyear = nextyear - -1;
    w('<a xmlns="" href="_' + nextyear + '.html">next</a>');
  } 
  
}
function leeg() 
{
}