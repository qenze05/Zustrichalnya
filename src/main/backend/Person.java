import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Person {

    private String surname;

    private String name;
    private String patronymic;
    private String nickname;
    private int nameVisibilityMode = Integer.MIN_VALUE;


    private HashMap<Image, Boolean> photos;



    private Date birthdate;
    private int age;
    private boolean showBirthdate;

    private String region;

    private String city;

    // Geoposition
    private String sex;

    private String gender;

    private String sexuality;

    private String occupation;

    private String university;

    private String profession;

    private boolean openForOneDay;
    private boolean openForFriendship;
    private boolean openForRomance;
    private boolean openForPartnership;
    private boolean openForFamily;

    private String description;


    private int introvertExtravert = Integer.MIN_VALUE;
    private int brainHeart = Integer.MIN_VALUE;
    private int darkLight = Integer.MIN_VALUE;
    private int conservatorInnovator = Integer.MIN_VALUE;
    private int pessimistOptimist = Integer.MIN_VALUE;
    private int pragmaticAvanturistic = Integer.MIN_VALUE;
    private int plannerImproviser = Integer.MIN_VALUE;
    private int homesitterTraveller = Integer.MIN_VALUE;
    private int practicalCreative = Integer.MIN_VALUE;
    private int calmEmotional = Integer.MIN_VALUE;
    private int docilePersistent = Integer.MIN_VALUE;
    private int egoistAltruist = Integer.MIN_VALUE;
    private int benefitseekerMoralist = Integer.MIN_VALUE;
    private int downtoearthAmbitious = Integer.MIN_VALUE;
    private int followerLeader = Integer.MIN_VALUE;
    private int modestUninhibited = Integer.MIN_VALUE;
    private int toughTender = Integer.MIN_VALUE;
    private int distrustfulTrusting = Integer.MIN_VALUE;
    private int seriousChill = Integer.MIN_VALUE;
    private int straightforwardIndirect = Integer.MIN_VALUE;

    private String personalityType;

    private ArrayList<String> subcultures;
    private HashMap<Hobby, Integer> hobbies;

    private HashMap<RomanticPreference, Integer> romanticPreferences;

    private String romanticPreferenceDescription;



    private Religion religion;

    private ArrayList<String> socialMovements;
    private String language;
    private double leftRight;
    private double libAuth;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public HashMap<Image, Boolean> getPhotos() {
        return photos;
    }

    public void setPhotos(HashMap<Image, Boolean> photos) {
        this.photos = photos;
    }

    public int getNameVisibilityMode() {
        return nameVisibilityMode;
    }

    public void setNameVisibilityMode(int nameVisibilityMode) {
        this.nameVisibilityMode = nameVisibilityMode;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isShowBirthdate() {
        return showBirthdate;
    }

    public void setShowBirthdate(boolean showBirthdate) {
        this.showBirthdate = showBirthdate;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSexuality() {
        return sexuality;
    }

    public void setSexuality(String sexuality) {
        this.sexuality = sexuality;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public boolean isOpenForOneDay() {
        return openForOneDay;
    }

    public void setOpenForOneDay(boolean openForOneDay) {
        this.openForOneDay = openForOneDay;
    }

    public boolean isOpenForFriendship() {
        return openForFriendship;
    }

    public void setOpenForFriendship(boolean openForFriendship) {
        this.openForFriendship = openForFriendship;
    }

    public boolean isOpenForRomance() {
        return openForRomance;
    }

    public void setOpenForRomance(boolean openForRomance) {
        this.openForRomance = openForRomance;
    }

    public boolean isOpenForPartnership() {
        return openForPartnership;
    }

    public void setOpenForPartnership(boolean openForPartnership) {
        this.openForPartnership = openForPartnership;
    }

    public boolean isOpenForFamily() {
        return openForFamily;
    }

    public void setOpenForFamily(boolean openForFamily) {
        this.openForFamily = openForFamily;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIntrovertExtravert() {
        return introvertExtravert;
    }

    public void setIntrovertExtravert(int introvertExtravert) {
        this.introvertExtravert = introvertExtravert;
    }

    public int getBrainHeart() {
        return brainHeart;
    }

    public void setBrainHeart(int brainHeart) {
        this.brainHeart = brainHeart;
    }

    public int getDarkLight() {
        return darkLight;
    }

    public void setDarkLight(int darkLight) {
        this.darkLight = darkLight;
    }

    public int getConservatorInnovator() {
        return conservatorInnovator;
    }

    public void setConservatorInnovator(int conservatorInnovator) {
        this.conservatorInnovator = conservatorInnovator;
    }

    public int getPessimistOptimist() {
        return pessimistOptimist;
    }

    public void setPessimistOptimist(int pessimistOptimist) {
        this.pessimistOptimist = pessimistOptimist;
    }

    public int getPragmaticAvanturistic() {
        return pragmaticAvanturistic;
    }

    public void setPragmaticAvanturistic(int pragmaticAvanturistic) {
        this.pragmaticAvanturistic = pragmaticAvanturistic;
    }

    public int getPlannerImproviser() {
        return plannerImproviser;
    }

    public void setPlannerImproviser(int plannerImproviser) {
        this.plannerImproviser = plannerImproviser;
    }

    public int getHomesitterTraveller() {
        return homesitterTraveller;
    }

    public void setHomesitterTraveller(int homesitterTraveller) {
        this.homesitterTraveller = homesitterTraveller;
    }

    public int getPracticalCreative() {
        return practicalCreative;
    }

    public void setPracticalCreative(int practicalCreative) {
        this.practicalCreative = practicalCreative;
    }

    public int getCalmEmotional() {
        return calmEmotional;
    }

    public void setCalmEmotional(int calmEmotional) {
        this.calmEmotional = calmEmotional;
    }

    public int getDocilePersistent() {
        return docilePersistent;
    }

    public void setDocilePersistent(int docilePersistent) {
        this.docilePersistent = docilePersistent;
    }

    public int getEgoistAltruist() {
        return egoistAltruist;
    }

    public void setEgoistAltruist(int egoistAltruist) {
        this.egoistAltruist = egoistAltruist;
    }

    public int getBenefitseekerMoralist() {
        return benefitseekerMoralist;
    }

    public void setBenefitseekerMoralist(int benefitseekerMoralist) {
        this.benefitseekerMoralist = benefitseekerMoralist;
    }

    public int getDowntoearthAmbitious() {
        return downtoearthAmbitious;
    }

    public void setDowntoearthAmbitious(int downtoearthAmbitious) {
        this.downtoearthAmbitious = downtoearthAmbitious;
    }

    public int getFollowerLeader() {
        return followerLeader;
    }

    public void setFollowerLeader(int followerLeader) {
        this.followerLeader = followerLeader;
    }

    public int getModestUninhibited() {
        return modestUninhibited;
    }

    public void setModestUninhibited(int modestUninhibited) {
        this.modestUninhibited = modestUninhibited;
    }

    public int getToughTender() {
        return toughTender;
    }

    public void setToughTender(int toughTender) {
        this.toughTender = toughTender;
    }

    public int getDistrustfulTrusting() {
        return distrustfulTrusting;
    }

    public void setDistrustfulTrusting(int distrustfulTrusting) {
        this.distrustfulTrusting = distrustfulTrusting;
    }

    public int getSeriousChill() {
        return seriousChill;
    }

    public void setSeriousChill(int seriousChill) {
        this.seriousChill = seriousChill;
    }

    public int getStraightforwardIndirect() {
        return straightforwardIndirect;
    }

    public void setStraightforwardIndirect(int straightforwardIndirect) {
        this.straightforwardIndirect = straightforwardIndirect;
    }

    public String getPersonalityType() {
        return personalityType;
    }

    public void setPersonalityType(String personalityType) {
        this.personalityType = personalityType;
    }

    public ArrayList<String> getSubcultures() {
        return subcultures;
    }

    public void setSubcultures(ArrayList<String> subcultures) {
        this.subcultures = subcultures;
    }

    public HashMap<Hobby, Integer> getHobbies() {
        return hobbies;
    }

    public void setHobbies(HashMap<Hobby, Integer> hobbies) {
        this.hobbies = hobbies;
    }

    public HashMap<RomanticPreference, Integer> getRomanticPreferences() {
        return romanticPreferences;
    }

    public void setRomanticPreferences(HashMap<RomanticPreference, Integer> romanticPreferences) {
        this.romanticPreferences = romanticPreferences;
    }

    public String getRomanticPreferenceDescription() {
        return romanticPreferenceDescription;
    }

    public void setRomanticPreferenceDescription(String romanticPreferenceDescription) {
        this.romanticPreferenceDescription = romanticPreferenceDescription;
    }

    public Religion getReligion() {
        return religion;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
    }

    public ArrayList<String> getSocialMovements() {
        return socialMovements;
    }

    public void setSocialMovements(ArrayList<String> socialMovements) {
        this.socialMovements = socialMovements;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public double getLeftRight() {
        return leftRight;
    }

    public void setLeftRight(double leftRight) {
        this.leftRight = leftRight;
    }

    public double getLibAuth() {
        return libAuth;
    }

    public void setLibAuth(double libAuth) {
        this.libAuth = libAuth;
    }

}
