/*
 * Created on Jul 9, 2006
 *
 */
package nl.home.ttilma.ped.dom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author ttilma
 */
@XmlRootElement
public class Animal {
	private static Log log = LogFactory.getLog("Animal");
	
	public static final int UNKNOWN = 0;
	public static final int MALE = 1;
	public static final int FEMALE = 2;
	
	private String animalid="";
	private String prevEngChId="";
	private String nextEngChId="";
	private String prevNlChId="";
	private String nextNlChId="";
//	private String prevRomId="";
//	private String nextRomId="";
//	private String prevRomcId="";
//	private String nextRomcId="";
//	private String prevRomaId="";
//	private String nextRomaId="";
//	private String prevRomnzId="";
//	private String nextRomnzId="";
	private String kennelid="";
	private String kennelid2;
	private String fatherId="";
	private String motherId="";
	private String startOfFamily;
	private String nameBeforeKennel;
	private String nameBeforeKennel2;
	private boolean cd;
	private boolean cdx;
	private boolean ud;
	private boolean udx;
	private boolean rom;
	private boolean romc;
	private boolean roma;
	private boolean romnz;
	private String cc;
	private String cw;
	private boolean jw;
	//private boolean champion;
	private String chyear;
	//private List<String> championList = new ArrayList<String>();
	private List<Champion> championList = new ArrayList<Champion>();
	private String name="";
	private String suffix="";
	//private String ownerId="";
	private String totalName="";
	
	private String color="";
	private String born="";
	private Date bornDate=null;
	//private String nickName="";
	private String line="";
	private String family="";
	private int gender = UNKNOWN;
	private int nChildren = 0;	
	//private double influence = 0.0;
	private Reg reg;
	private boolean hasPhoto;
	private String exportFrom;
	private String importTo;
	
	
	public Animal() {
		log.debug("creating Animal");
	}
	
	/**
	 * @return Returns the id.
	 */
	@XmlAttribute
	public String getAnimalid() {
		return animalid;
	}
	
	/**
	 * @param id The id to set.
	 */
	public void setAnimalid(String id) {
		this.animalid = id;
	}
	
	/**
	 * @return Returns the kennelid.
	 */
	@XmlAttribute(name="kennelid")
	public String getKennelid() {
        return kennelid;
	}
	
	/**
	 * @param kennelId
	 *            The kennelId to set.
	 */
	public void setKennelid(String kennelId) {
	    this.kennelid = kennelId;
	}
	
	/**
	 * @return Returns the name.
	 */
	@XmlElement
	public String getName() {
		return name;
	}
	
	/**
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return Returns the nameBeforeKennel.
	 */
	@XmlElement (name="namebeforekennel")
	public String getNameBeforeKennel() {
		return nameBeforeKennel;
	}
	
	/**
	 * @param nameBeforeKennel
	 *            The nameBeforeKennel to set.
	 */
	public void setNameBeforeKennel(String nameBeforeKennel) {
		this.nameBeforeKennel = nameBeforeKennel;
	}
	
	/**
	 * @return Returns the champion.
	 */
	public boolean isChampion() {
		return !championList.isEmpty();
	}
	
	public boolean isChampion(String country) {
		log.info("isChampion country:" + country);
		if (country == null || "".equals(country)) {
			return false;
		} else {
			return championList.contains(country);
		}
	}
	
//	public String getChampion(int idx) {
//		if (championList.isEmpty()) {
//			return "";
//		} else {
//			return (String) championList.get(idx);
//		}
//	}
	
//	public void addChampion(String country) {
//		log.info("addChampion: " + country);
//		//champion = true;
//		championList.add(country);
//	}
	
	@XmlElement
	public List<Champion> getCh() {
		return championList;
	}
	
	/**
	 * @return Returns the fatherId.
	 */
    @XmlAttribute(name="fatherid")
	public String getFatherId() {
		return fatherId;
	}
	
	/**
	 * @param fatherId
	 *            The fatherId to set.
	 */
	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}
	
	/**
	 * @return Returns the motherId.
	 */
	@XmlAttribute(name="motherid")
	public String getMotherId() {
		return motherId;
	}
	
	/**
	 * @param motherId
	 *            The motherId to set.
	 */
	public void setMotherId(String motherId) {
		this.motherId = motherId;
	}
	
	/**
	 * @return Returns the champTitles.
	 */
	//public String getChampTitles() {
	//	return champTitles;
	//}
	
	/**
	 * @param champTitles
	 *            The champTitles to set.
	 */
	//public void setChampTitles(String champTitles) {
	//	this.champTitles = champTitles;
		//champion = champTitles != null;
	//}
	
	/**
	 * @return Returns the color.
	 */
	@XmlAttribute
	public String getColor() {
		return color;
	}
	
	/**
	 * @param color
	 *            The color to set.
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	/**
	 * @return Returns the totalName.
	 */
	public String getTotalName() {
		return totalName;
	}
	
	/**
	 * @param totalName
	 *            The totalName to set.
	 */
	public void setTotalName(String totalName) {
		this.totalName = totalName;
	}
	
	/**
	 * @return Returns the startOfFamily.
	 */
	@XmlAttribute(name="startof")
	public String getStartOfFamily() {
		return startOfFamily;
	}
	
	/**
	 * @param startOfFamily
	 *            The startOfFamily to set.
	 */
	public void setStartOfFamily(String startOfFamily) {
		this.startOfFamily = startOfFamily;
	}
	
	/**
	 * @return Returns the born.
	 */
    @XmlElement
	public String getBorn() {
		return born;
	}
	
	/**
	 * @param born
	 *            The born to set.
	 */
	public void setBorn(String born) {
		this.born = born;
	}
	

	
	/**
	 * @return Returns the cd.
	 */
	public boolean isCd() {
		return cd;
	}
	
	/**
	 * @param cd
	 *            The cd to set.
	 */
	public void setCd(boolean cd) {
		this.cd = cd;
	}
	
	/**
	 * @return Returns the cdx.
	 */
	public boolean isCdx() {
		return cdx;
	}
	
	/**
	 * @param cdx
	 *            The cdx to set.
	 */
	public void setCdx(boolean cdx) {
		this.cdx = cdx;
	}
	
	/**
	 * @return Returns the cw.
	 */
    @XmlElement
	public boolean isCw() {
		return cw != null;
	}
	
	/**
	 * @return Returns the cw.
	 */
    @XmlElement(name="CW")
	public String getCw() {
		return cw;
	}
	
	/**
	 * @param cw
	 *            The cw to set.
	 */
	public void setCw(String cw) {
		this.cw = cw;
	}
	
	/**
	 * @return Returns the rom.
	 */
    @XmlElement(name="ROM")
	public boolean isRom() {
		return rom;
	}
	
	/**
	 * @param rom
	 *            The rom to set.
	 */
	public void setRom(boolean rom) {
		this.rom = rom;
	}
	
	/**
	 * @return Returns the jw.
	 */
	@XmlElement(name="JW")
	public boolean isJw() {
		return jw;
	}
	
	/**
	 * @param jw
	 *            The jw to set.
	 */
	public void setJw(boolean jw) {
		this.jw = jw;
	}
	
	/**
	 * @return Returns the kennelId2.
	 */
	@XmlAttribute(name="kennelid2")
	public String getKennelId2() {
		return kennelid2;
	}
	
	/**
	 * @param kennelId2
	 *            The kennelId2 to set.
	 */
	public void setKennelId2(String kennelId2) {
		this.kennelid2 = kennelId2;
	}
	
	/**
	 * @return Returns the nameBeforeKennel2.
	 */
	@XmlElement (name="namebeforekennel2")
	public String getNameBeforeKennel2() {
		return nameBeforeKennel2;
	}
	
	/**
	 * @param nameBeforeKennel2
	 *            The nameBeforeKennel2 to set.
	 */
	public void setNameBeforeKennel2(String nameBeforeKennel2) {
		this.nameBeforeKennel2 = nameBeforeKennel2;
	}
	
	/**
	 * @return Returns the chyear.
	 */
	@XmlElement(name="chyear")
	public String getChyear() {
		return chyear;
	}
	
	/**
	 * @param chyear
	 *            The chyear to set.
	 */
	public void setChyear(String chyear) {
		this.chyear = chyear;
	}
	
	/**
	 * @return Returns the gender.
	 */
	@XmlElement
	public int getGender() {
		return gender;
	}
	
	/**
	 * @param gender
	 *            The gender to set.
	 */
	public void setGender(int gender) {
		//System.out.println("setting gender to " + gender);
		this.gender = gender;
	}
	
	/**
	 * @return Returns the nChildren.
	 */
	public int getNChildren() {
		return nChildren;
	}
	
	/**
	 * @param children
	 *            The nChildren to set.
	 */
	public void setNChildren(int children) {
		nChildren = children;
	}
	
	/**
	 * @param children
	 *            The nChildren to set.
	 */
	public void addChild() {
		nChildren++;
	}
	
	/**
	 * @return Returns the nextEngChId.
	 */
	public String getNextEngChId() {
		return nextEngChId;
	}
	
	/**
	 * @param nextEngChId
	 *            The nextEngChId to set.
	 */
	public void setNextEngChId(String nextEngChId) {
		this.nextEngChId = nextEngChId;
	}
	
	/**
	 * @return Returns the nextNlChId.
	 */
	public String getNextNlChId() {
		return nextNlChId;
	}
	
	/**
	 * @param nextNlChId
	 *            The nextNlChId to set.
	 */
	public void setNextNlChId(String nextNlChId) {
		this.nextNlChId = nextNlChId;
	}
	
	/**
	 * @return Returns the prevEngChId.
	 */
	public String getPrevEngChId() {
		return prevEngChId;
	}
	
	/**
	 * @param prevEngChId
	 *            The prevEngChId to set.
	 */
	public void setPrevEngChId(String prevEngChId) {
		this.prevEngChId = prevEngChId;
	}
	
	/**
	 * @return Returns the prevNlChId.
	 */
	public String getPrevNlChId() {
		return prevNlChId;
	}
	
	/**
	 * @param prevNlChId
	 *            The prevNlChId to set.
	 */
	public void setPrevNlChId(String prevNlChId) {
		this.prevNlChId = prevNlChId;
	}
	
	@XmlElement(name="ROMC")
	public boolean isRomc() {
		return romc;
	}
	
	public void setRomc(boolean romc) {
		this.romc = romc;
	}
	
    @XmlElement(name="ROMA")
	public boolean isRoma() {
		return roma;
	}
	
	public void setRoma(boolean roma) {
		this.roma = roma;
	}
	@XmlElement(name="ROMNZ")
	public boolean isRomnz() {
		return romnz;
	}
	
	public void setRomnz(boolean romnz) {
		this.romnz = romnz;
	}
	@XmlElement
	public String getSuffix() {
		return suffix;
	}
	
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	@XmlElement(name="UD")
	public boolean isUd() {
		return ud;
	}
	
	public void setUd(boolean ud) {
		this.ud = ud;
	}
	@XmlElement
	public boolean isUdx() {
		return udx;
	}
	
	public void setUdx(boolean udx) {
		this.udx = udx;
	}
	
	//public String getFamily() {
	//	return family;
	//}
	
	//public void setFamily(String family) {
	//	this.family = family;
	//}
	@XmlElement
	public String getFam() {
		return family;
	}
	
	public void setFam(String family) {
		this.family = family;
	}
	@XmlElement
	public String getLine() {
		return line;
	}
	
	public void setLine(String line) {
		this.line = line;
	}
	@XmlElement
	public Reg getReg() {
		return reg;
	}
	
	public void setReg(Reg reg) {
		this.reg = reg;
		log.debug("Adding REG for " + this.animalid + " " + reg.getRegYear() + " " + reg.getRegMonth());
	}
	
	
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public Date getBornDate() {
		return bornDate;
	}
	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}
//	public String getNextRomaId() {
//		return nextRomaId;
//	}
//	public void setNextRomaId(String nextRomaId) {
//		this.nextRomaId = nextRomaId;
//	}
//	public String getNextRomnzId() {
//		return nextRomnzId;
//	}
//	public void setNextRomnzId(String nextRomnzId) {
//		this.nextRomnzId = nextRomnzId;
//	}
//	public String getNextRomcId() {
//		return nextRomcId;
//	}
//	public void setNextRomcId(String nextRomcId) {
//		this.nextRomcId = nextRomcId;
//	}
//	public String getNextRomId() {
//		return nextRomId;
//	}
//	public void setNextRomId(String nextRomId) {
//		this.nextRomId = nextRomId;
//	}
//	public String getPrevRomaId() {
//		return prevRomaId;
//	}
//	public void setPrevRomaId(String prevRomaId) {
//		this.prevRomaId = prevRomaId;
//	}
//	public String getPrevRomnzId() {
//		return prevRomnzId;
//	}
//	public void setPrevRomnzId(String prevRomnzId) {
//		this.prevRomnzId = prevRomnzId;
//	}
//	public String getPrevRomcId() {
//		return prevRomcId;
//	}
//	public void setPrevRomcId(String prevRomcId) {
//		this.prevRomcId = prevRomcId;
//	}
//	public String getPrevRomId() {
//		return prevRomId;
//	}
//	public void setPrevRomId(String prevRomId) {
//		this.prevRomId = prevRomId;
//	}
	public boolean containsPhoto() {
		return hasPhoto;
	}
	public void setHasPhoto(boolean hasPhoto) {
		this.hasPhoto = hasPhoto;
	}
	@XmlElement(name="exportfrom")
	public String getExportFrom() {
		return exportFrom;
	}
	public void setExportFrom(String exportFrom) {
		this.exportFrom = exportFrom;
	}
    @XmlElement(name="importto")
	public String getImportTo() {
		return importTo;
	}
	public void setImportTo(String importTo) {
		this.importTo = importTo;
	}
}
