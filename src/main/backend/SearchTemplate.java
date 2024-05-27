import java.util.ArrayList;
import java.util.HashMap;

public class SearchTemplate {

    private String name;
    private String type;
    private String description;
    private ArrayList<String> greenFlags;
    private ArrayList<String> redFlags;
    private int realPhotoImportance;
    private double maleDesire;
    private double femaleDesire;
    private double otherDesire;
    private int minAge;
    private int maxAge;
    private int geoType;
    private int desiredDistance;
    private int allowedDistance;
    private int geolocationImportance;

    private int minIntrovertExtravert = Integer.MIN_VALUE;
    private int minBrainHeart = Integer.MIN_VALUE;
    private int minDarkLight = Integer.MIN_VALUE;
    private int minConservatorInnovator = Integer.MIN_VALUE;
    private int minPessimistOptimist = Integer.MIN_VALUE;
    private int minPragmaticAvanturistic = Integer.MIN_VALUE;
    private int minPlannerImproviser = Integer.MIN_VALUE;
    private int minHomesitterTraveller = Integer.MIN_VALUE;
    private int minPracticalCreative = Integer.MIN_VALUE;
    private int minCalmEmotional = Integer.MIN_VALUE;
    private int minDocilePersistent = Integer.MIN_VALUE;
    private int minEgoistAltruist = Integer.MIN_VALUE;
    private int minBenefitseekerMoralist = Integer.MIN_VALUE;
    private int minDowntoearthAmbitious = Integer.MIN_VALUE;
    private int minFollowerLeader = Integer.MIN_VALUE;
    private int minModestUninhibited = Integer.MIN_VALUE;
    private int minToughTender = Integer.MIN_VALUE;
    private int minDistrustfulTrusting = Integer.MIN_VALUE;
    private int minSeriousChill = Integer.MIN_VALUE;
    private int minStraightforwardIndirect = Integer.MIN_VALUE;


    private int maxIntrovertExtravert = Integer.MAX_VALUE;
    private int maxBrainHeart = Integer.MAX_VALUE;
    private int maxDarkLight = Integer.MAX_VALUE;
    private int maxConservatorInnovator = Integer.MAX_VALUE;
    private int maxPessimistOptimist = Integer.MAX_VALUE;
    private int maxPragmaticAvanturistic = Integer.MAX_VALUE;
    private int maxPlannerImproviser = Integer.MAX_VALUE;
    private int maxHomesitterTraveller = Integer.MAX_VALUE;
    private int maxPracticalCreative = Integer.MAX_VALUE;
    private int maxCalmEmotional = Integer.MAX_VALUE;
    private int maxDocilePersistent = Integer.MAX_VALUE;
    private int maxEgoistAltruist = Integer.MAX_VALUE;
    private int maxBenefitseekerMoralist = Integer.MAX_VALUE;
    private int maxDowntoearthAmbitious = Integer.MAX_VALUE;
    private int maxFollowerLeader = Integer.MAX_VALUE;
    private int maxModestUninhibited = Integer.MAX_VALUE;
    private int maxToughTender = Integer.MAX_VALUE;
    private int maxDistrustfulTrusting = Integer.MAX_VALUE;
    private int maxSeriousChill = Integer.MAX_VALUE;
    private int maxStraightforwardIndirect = Integer.MAX_VALUE;

    private int introvertExtravertImportance = Integer.MIN_VALUE;
    private int brainHeartImportance = Integer.MIN_VALUE;
    private int darkLightImportance = Integer.MIN_VALUE;
    private int conservatorInnovatorImportance = Integer.MIN_VALUE;
    private int pessimistOptimistImportance = Integer.MIN_VALUE;
    private int pragmaticAvanturisticImportance = Integer.MIN_VALUE;
    private int plannerImproviserImportance = Integer.MIN_VALUE;
    private int homesitterTravellerImportance = Integer.MIN_VALUE;
    private int practicalCreativeImportance = Integer.MIN_VALUE;
    private int calmEmotionalImportance = Integer.MIN_VALUE;
    private int docilePersistentImportance = Integer.MIN_VALUE;
    private int egoistAltruistImportance = Integer.MIN_VALUE;
    private int benefitseekerMoralistImportance = Integer.MIN_VALUE;
    private int downtoearthAmbitiousImportance = Integer.MIN_VALUE;
    private int followerLeaderImportance = Integer.MIN_VALUE;
    private int modestUninhibitedImportance = Integer.MIN_VALUE;
    private int toughTenderImportance = Integer.MIN_VALUE;
    private int distrustfulTrustingImportance = Integer.MIN_VALUE;
    private int seriousChillImportance = Integer.MIN_VALUE;
    private int straightforwardIndirectImportance = Integer.MIN_VALUE;

    private HashMap<String, Integer> personalityTypeFilter;
    private HashMap<String, Integer> subculturesFilter;
    private HashMap<Hobby, Integer> hobbiesFilter;
    private HashMap<RomanticPreference, Integer> romanticPreferencesFilter;
    private HashMap<String, Integer> religionFilter;
    private HashMap<String, Integer> socialMovementsFilter;
    private HashMap<String, Integer> languageFilter;
    private double leftRightCoord;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getGreenFlags() {
        return greenFlags;
    }

    public void setGreenFlags(ArrayList<String> greenFlags) {
        this.greenFlags = greenFlags;
    }

    public ArrayList<String> getRedFlags() {
        return redFlags;
    }

    public void setRedFlags(ArrayList<String> redFlags) {
        this.redFlags = redFlags;
    }

    public int getRealPhotoImportance() {
        return realPhotoImportance;
    }

    public void setRealPhotoImportance(int realPhotoImportance) {
        this.realPhotoImportance = realPhotoImportance;
    }

    public double getMaleDesire() {
        return maleDesire;
    }

    public void setMaleDesire(double maleDesire) {
        this.maleDesire = maleDesire;
    }

    public double getFemaleDesire() {
        return femaleDesire;
    }

    public void setFemaleDesire(double femaleDesire) {
        this.femaleDesire = femaleDesire;
    }

    public double getOtherDesire() {
        return otherDesire;
    }

    public void setOtherDesire(double otherDesire) {
        this.otherDesire = otherDesire;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public int getGeoType() {
        return geoType;
    }

    public void setGeoType(int geoType) {
        this.geoType = geoType;
    }

    public int getDesiredDistance() {
        return desiredDistance;
    }

    public void setDesiredDistance(int desiredDistance) {
        this.desiredDistance = desiredDistance;
    }

    public int getAllowedDistance() {
        return allowedDistance;
    }

    public void setAllowedDistance(int allowedDistance) {
        this.allowedDistance = allowedDistance;
    }

    public int getGeolocationImportance() {
        return geolocationImportance;
    }

    public void setGeolocationImportance(int geolocationImportance) {
        this.geolocationImportance = geolocationImportance;
    }

    public int getMinIntrovertExtravert() {
        return minIntrovertExtravert;
    }

    public void setMinIntrovertExtravert(int minIntrovertExtravert) {
        this.minIntrovertExtravert = minIntrovertExtravert;
    }

    public int getMinBrainHeart() {
        return minBrainHeart;
    }

    public void setMinBrainHeart(int minBrainHeart) {
        this.minBrainHeart = minBrainHeart;
    }

    public int getMinDarkLight() {
        return minDarkLight;
    }

    public void setMinDarkLight(int minDarkLight) {
        this.minDarkLight = minDarkLight;
    }

    public int getMinConservatorInnovator() {
        return minConservatorInnovator;
    }

    public void setMinConservatorInnovator(int minConservatorInnovator) {
        this.minConservatorInnovator = minConservatorInnovator;
    }

    public int getMinPessimistOptimist() {
        return minPessimistOptimist;
    }

    public void setMinPessimistOptimist(int minPessimistOptimist) {
        this.minPessimistOptimist = minPessimistOptimist;
    }

    public int getMinPragmaticAvanturistic() {
        return minPragmaticAvanturistic;
    }

    public void setMinPragmaticAvanturistic(int minPragmaticAvanturistic) {
        this.minPragmaticAvanturistic = minPragmaticAvanturistic;
    }

    public int getMinPlannerImproviser() {
        return minPlannerImproviser;
    }

    public void setMinPlannerImproviser(int minPlannerImproviser) {
        this.minPlannerImproviser = minPlannerImproviser;
    }

    public int getMinHomesitterTraveller() {
        return minHomesitterTraveller;
    }

    public void setMinHomesitterTraveller(int minHomesitterTraveller) {
        this.minHomesitterTraveller = minHomesitterTraveller;
    }

    public int getMinPracticalCreative() {
        return minPracticalCreative;
    }

    public void setMinPracticalCreative(int minPracticalCreative) {
        this.minPracticalCreative = minPracticalCreative;
    }

    public int getMinCalmEmotional() {
        return minCalmEmotional;
    }

    public void setMinCalmEmotional(int minCalmEmotional) {
        this.minCalmEmotional = minCalmEmotional;
    }

    public int getMinDocilePersistent() {
        return minDocilePersistent;
    }

    public void setMinDocilePersistent(int minDocilePersistent) {
        this.minDocilePersistent = minDocilePersistent;
    }

    public int getMinEgoistAltruist() {
        return minEgoistAltruist;
    }

    public void setMinEgoistAltruist(int minEgoistAltruist) {
        this.minEgoistAltruist = minEgoistAltruist;
    }

    public int getMinBenefitseekerMoralist() {
        return minBenefitseekerMoralist;
    }

    public void setMinBenefitseekerMoralist(int minBenefitseekerMoralist) {
        this.minBenefitseekerMoralist = minBenefitseekerMoralist;
    }

    public int getMinDowntoearthAmbitious() {
        return minDowntoearthAmbitious;
    }

    public void setMinDowntoearthAmbitious(int minDowntoearthAmbitious) {
        this.minDowntoearthAmbitious = minDowntoearthAmbitious;
    }

    public int getMinFollowerLeader() {
        return minFollowerLeader;
    }

    public void setMinFollowerLeader(int minFollowerLeader) {
        this.minFollowerLeader = minFollowerLeader;
    }

    public int getMinModestUninhibited() {
        return minModestUninhibited;
    }

    public void setMinModestUninhibited(int minModestUninhibited) {
        this.minModestUninhibited = minModestUninhibited;
    }

    public int getMinToughTender() {
        return minToughTender;
    }

    public void setMinToughTender(int minToughTender) {
        this.minToughTender = minToughTender;
    }

    public int getMinDistrustfulTrusting() {
        return minDistrustfulTrusting;
    }

    public void setMinDistrustfulTrusting(int minDistrustfulTrusting) {
        this.minDistrustfulTrusting = minDistrustfulTrusting;
    }

    public int getMinSeriousChill() {
        return minSeriousChill;
    }

    public void setMinSeriousChill(int minSeriousChill) {
        this.minSeriousChill = minSeriousChill;
    }

    public int getMinStraightforwardIndirect() {
        return minStraightforwardIndirect;
    }

    public void setMinStraightforwardIndirect(int minStraightforwardIndirect) {
        this.minStraightforwardIndirect = minStraightforwardIndirect;
    }

    public int getMaxIntrovertExtravert() {
        return maxIntrovertExtravert;
    }

    public void setMaxIntrovertExtravert(int maxIntrovertExtravert) {
        this.maxIntrovertExtravert = maxIntrovertExtravert;
    }

    public int getMaxBrainHeart() {
        return maxBrainHeart;
    }

    public void setMaxBrainHeart(int maxBrainHeart) {
        this.maxBrainHeart = maxBrainHeart;
    }

    public int getMaxDarkLight() {
        return maxDarkLight;
    }

    public void setMaxDarkLight(int maxDarkLight) {
        this.maxDarkLight = maxDarkLight;
    }

    public int getMaxConservatorInnovator() {
        return maxConservatorInnovator;
    }

    public void setMaxConservatorInnovator(int maxConservatorInnovator) {
        this.maxConservatorInnovator = maxConservatorInnovator;
    }

    public int getMaxPessimistOptimist() {
        return maxPessimistOptimist;
    }

    public void setMaxPessimistOptimist(int maxPessimistOptimist) {
        this.maxPessimistOptimist = maxPessimistOptimist;
    }

    public int getMaxPragmaticAvanturistic() {
        return maxPragmaticAvanturistic;
    }

    public void setMaxPragmaticAvanturistic(int maxPragmaticAvanturistic) {
        this.maxPragmaticAvanturistic = maxPragmaticAvanturistic;
    }

    public int getMaxPlannerImproviser() {
        return maxPlannerImproviser;
    }

    public void setMaxPlannerImproviser(int maxPlannerImproviser) {
        this.maxPlannerImproviser = maxPlannerImproviser;
    }

    public int getMaxHomesitterTraveller() {
        return maxHomesitterTraveller;
    }

    public void setMaxHomesitterTraveller(int maxHomesitterTraveller) {
        this.maxHomesitterTraveller = maxHomesitterTraveller;
    }

    public int getMaxPracticalCreative() {
        return maxPracticalCreative;
    }

    public void setMaxPracticalCreative(int maxPracticalCreative) {
        this.maxPracticalCreative = maxPracticalCreative;
    }

    public int getMaxCalmEmotional() {
        return maxCalmEmotional;
    }

    public void setMaxCalmEmotional(int maxCalmEmotional) {
        this.maxCalmEmotional = maxCalmEmotional;
    }

    public int getMaxDocilePersistent() {
        return maxDocilePersistent;
    }

    public void setMaxDocilePersistent(int maxDocilePersistent) {
        this.maxDocilePersistent = maxDocilePersistent;
    }

    public int getMaxEgoistAltruist() {
        return maxEgoistAltruist;
    }

    public void setMaxEgoistAltruist(int maxEgoistAltruist) {
        this.maxEgoistAltruist = maxEgoistAltruist;
    }

    public int getMaxBenefitseekerMoralist() {
        return maxBenefitseekerMoralist;
    }

    public void setMaxBenefitseekerMoralist(int maxBenefitseekerMoralist) {
        this.maxBenefitseekerMoralist = maxBenefitseekerMoralist;
    }

    public int getMaxDowntoearthAmbitious() {
        return maxDowntoearthAmbitious;
    }

    public void setMaxDowntoearthAmbitious(int maxDowntoearthAmbitious) {
        this.maxDowntoearthAmbitious = maxDowntoearthAmbitious;
    }

    public int getMaxFollowerLeader() {
        return maxFollowerLeader;
    }

    public void setMaxFollowerLeader(int maxFollowerLeader) {
        this.maxFollowerLeader = maxFollowerLeader;
    }

    public int getMaxModestUninhibited() {
        return maxModestUninhibited;
    }

    public void setMaxModestUninhibited(int maxModestUninhibited) {
        this.maxModestUninhibited = maxModestUninhibited;
    }

    public int getMaxToughTender() {
        return maxToughTender;
    }

    public void setMaxToughTender(int maxToughTender) {
        this.maxToughTender = maxToughTender;
    }

    public int getMaxDistrustfulTrusting() {
        return maxDistrustfulTrusting;
    }

    public void setMaxDistrustfulTrusting(int maxDistrustfulTrusting) {
        this.maxDistrustfulTrusting = maxDistrustfulTrusting;
    }

    public int getMaxSeriousChill() {
        return maxSeriousChill;
    }

    public void setMaxSeriousChill(int maxSeriousChill) {
        this.maxSeriousChill = maxSeriousChill;
    }

    public int getMaxStraightforwardIndirect() {
        return maxStraightforwardIndirect;
    }

    public void setMaxStraightforwardIndirect(int maxStraightforwardIndirect) {
        this.maxStraightforwardIndirect = maxStraightforwardIndirect;
    }

    public int getIntrovertExtravertImportance() {
        return introvertExtravertImportance;
    }

    public void setIntrovertExtravertImportance(int introvertExtravertImportance) {
        this.introvertExtravertImportance = introvertExtravertImportance;
    }

    public int getBrainHeartImportance() {
        return brainHeartImportance;
    }

    public void setBrainHeartImportance(int brainHeartImportance) {
        this.brainHeartImportance = brainHeartImportance;
    }

    public int getDarkLightImportance() {
        return darkLightImportance;
    }

    public void setDarkLightImportance(int darkLightImportance) {
        this.darkLightImportance = darkLightImportance;
    }

    public int getConservatorInnovatorImportance() {
        return conservatorInnovatorImportance;
    }

    public void setConservatorInnovatorImportance(int conservatorInnovatorImportance) {
        this.conservatorInnovatorImportance = conservatorInnovatorImportance;
    }

    public int getPessimistOptimistImportance() {
        return pessimistOptimistImportance;
    }

    public void setPessimistOptimistImportance(int pessimistOptimistImportance) {
        this.pessimistOptimistImportance = pessimistOptimistImportance;
    }

    public int getPragmaticAvanturisticImportance() {
        return pragmaticAvanturisticImportance;
    }

    public void setPragmaticAvanturisticImportance(int pragmaticAvanturisticImportance) {
        this.pragmaticAvanturisticImportance = pragmaticAvanturisticImportance;
    }

    public int getPlannerImproviserImportance() {
        return plannerImproviserImportance;
    }

    public void setPlannerImproviserImportance(int plannerImproviserImportance) {
        this.plannerImproviserImportance = plannerImproviserImportance;
    }

    public int getHomesitterTravellerImportance() {
        return homesitterTravellerImportance;
    }

    public void setHomesitterTravellerImportance(int homesitterTravellerImportance) {
        this.homesitterTravellerImportance = homesitterTravellerImportance;
    }

    public int getPracticalCreativeImportance() {
        return practicalCreativeImportance;
    }

    public void setPracticalCreativeImportance(int practicalCreativeImportance) {
        this.practicalCreativeImportance = practicalCreativeImportance;
    }

    public int getCalmEmotionalImportance() {
        return calmEmotionalImportance;
    }

    public void setCalmEmotionalImportance(int calmEmotionalImportance) {
        this.calmEmotionalImportance = calmEmotionalImportance;
    }

    public int getDocilePersistentImportance() {
        return docilePersistentImportance;
    }

    public void setDocilePersistentImportance(int docilePersistentImportance) {
        this.docilePersistentImportance = docilePersistentImportance;
    }

    public int getEgoistAltruistImportance() {
        return egoistAltruistImportance;
    }

    public void setEgoistAltruistImportance(int egoistAltruistImportance) {
        this.egoistAltruistImportance = egoistAltruistImportance;
    }

    public int getBenefitseekerMoralistImportance() {
        return benefitseekerMoralistImportance;
    }

    public void setBenefitseekerMoralistImportance(int benefitseekerMoralistImportance) {
        this.benefitseekerMoralistImportance = benefitseekerMoralistImportance;
    }

    public int getDowntoearthAmbitiousImportance() {
        return downtoearthAmbitiousImportance;
    }

    public void setDowntoearthAmbitiousImportance(int downtoearthAmbitiousImportance) {
        this.downtoearthAmbitiousImportance = downtoearthAmbitiousImportance;
    }

    public int getFollowerLeaderImportance() {
        return followerLeaderImportance;
    }

    public void setFollowerLeaderImportance(int followerLeaderImportance) {
        this.followerLeaderImportance = followerLeaderImportance;
    }

    public int getModestUninhibitedImportance() {
        return modestUninhibitedImportance;
    }

    public void setModestUninhibitedImportance(int modestUninhibitedImportance) {
        this.modestUninhibitedImportance = modestUninhibitedImportance;
    }

    public int getToughTenderImportance() {
        return toughTenderImportance;
    }

    public void setToughTenderImportance(int toughTenderImportance) {
        this.toughTenderImportance = toughTenderImportance;
    }

    public int getDistrustfulTrustingImportance() {
        return distrustfulTrustingImportance;
    }

    public void setDistrustfulTrustingImportance(int distrustfulTrustingImportance) {
        this.distrustfulTrustingImportance = distrustfulTrustingImportance;
    }

    public int getSeriousChillImportance() {
        return seriousChillImportance;
    }

    public void setSeriousChillImportance(int seriousChillImportance) {
        this.seriousChillImportance = seriousChillImportance;
    }

    public int getStraightforwardIndirectImportance() {
        return straightforwardIndirectImportance;
    }

    public void setStraightforwardIndirectImportance(int straightforwardIndirectImportance) {
        this.straightforwardIndirectImportance = straightforwardIndirectImportance;
    }

    public HashMap<String, Integer> getPersonalityTypeFilter() {
        return personalityTypeFilter;
    }

    public void setPersonalityTypeFilter(HashMap<String, Integer> personalityTypeFilter) {
        this.personalityTypeFilter = personalityTypeFilter;
    }

    public HashMap<String, Integer> getSubculturesFilter() {
        return subculturesFilter;
    }

    public void setSubculturesFilter(HashMap<String, Integer> subculturesFilter) {
        this.subculturesFilter = subculturesFilter;
    }

    public HashMap<Hobby, Integer> getHobbiesFilter() {
        return hobbiesFilter;
    }

    public void setHobbiesFilter(HashMap<Hobby, Integer> hobbiesFilter) {
        this.hobbiesFilter = hobbiesFilter;
    }

    public HashMap<RomanticPreference, Integer> getRomanticPreferencesFilter() {
        return romanticPreferencesFilter;
    }

    public void setRomanticPreferencesFilter(HashMap<RomanticPreference, Integer> romanticPreferencesFilter) {
        this.romanticPreferencesFilter = romanticPreferencesFilter;
    }

    public HashMap<String, Integer> getReligionFilter() {
        return religionFilter;
    }

    public void setReligionFilter(HashMap<String, Integer> religionFilter) {
        this.religionFilter = religionFilter;
    }

    public HashMap<String, Integer> getSocialMovementsFilter() {
        return socialMovementsFilter;
    }

    public void setSocialMovementsFilter(HashMap<String, Integer> socialMovementsFilter) {
        this.socialMovementsFilter = socialMovementsFilter;
    }

    public HashMap<String, Integer> getLanguageFilter() {
        return languageFilter;
    }

    public void setLanguageFilter(HashMap<String, Integer> languageFilter) {
        this.languageFilter = languageFilter;
    }

    public double getLeftRightCoord() {
        return leftRightCoord;
    }

    public void setLeftRightCoord(double leftRightCoord) {
        this.leftRightCoord = leftRightCoord;
    }

    public double getLibAuthCoord() {
        return libAuthCoord;
    }

    public void setLibAuthCoord(double libAuthCoord) {
        this.libAuthCoord = libAuthCoord;
    }

    public double getDesiredRadius() {
        return desiredRadius;
    }

    public void setDesiredRadius(double desiredRadius) {
        this.desiredRadius = desiredRadius;
    }

    public double getAllowedRadius() {
        return allowedRadius;
    }

    public void setAllowedRadius(double allowedRadius) {
        this.allowedRadius = allowedRadius;
    }

    private double libAuthCoord;
    private double desiredRadius;
    private double allowedRadius;
}
