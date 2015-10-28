package com.aiworker.stellarbirthday;

import java.util.Arrays;

/** class to initialize StarName's/Distances and calculate birthday Star*/
public class Stellar {
    //	public static float[][] DistanceInDays= new float[3][3];
    public static int NumberOfStars = 19;
    public static float[] DistanceInDays= new float[NumberOfStars];
    public static String[] StarNames= new String[NumberOfStars];
    public static String[] StarInfo= new String[NumberOfStars];
    public static String[] StarFunnyInfo= new String[NumberOfStars];
    public static int index = 0;
    public static float DaysToStellarBirthday = 0;

    /** create 2D array with StarName's and Distance in light years, days */
    static void iniStarsArray() {
//    	DistanceInDays[0][0] = 1f; DistanceInDays[0][1] = 4.0f;	 StarNames[0] = "Toliman";
//    	DistanceInDays[1][0] = 2f; DistanceInDays[1][1] = 6.0f;	 StarNames[1] = "Vega";
//    	DistanceInDays[2][0] = 2f; DistanceInDays[2][1] = 10.0f; StarNames[2] = "Sirius";
        DistanceInDays[0] = 0f;	 	 StarNames[0] = "Earth";     	StarInfo[0] = "home planet";
        DistanceInDays[1] = 1604f;	 StarNames[1] = "Alpha Centauri";		StarInfo[1] = "Alpha Centauri is the sun's closest neighbor";
        DistanceInDays[2] = 3139f;	 StarNames[2] = "Sirius";		StarInfo[2] = "Sirius is the brightest star in the Earth's night sky";
        DistanceInDays[3] = 4183f;   StarNames[3] = "Procyon";		StarInfo[3] = "Procyon is a hound belonging to Erigone, daughter of Icarius";
        DistanceInDays[4] = 6105f;	 StarNames[4] = "Altair";		StarInfo[4] = "Altair is an abbreviation of the Arabic phrase an-nasr - the 'flying eagle'";
        DistanceInDays[5] = 9140f;	 StarNames[5] = "Vega";			StarInfo[5] = "When you look at Vega, you look in the motion direction of the solar system";
        DistanceInDays[6] = 9169f;   StarNames[6] = "Fomalhaut";	StarInfo[6] = "Ptolemy put Fomalhaut in Aquarius";
        DistanceInDays[7] = 12329f;	 StarNames[7] = "Pollux";		StarInfo[7] = "Pollux is twin brother of Castor";
        DistanceInDays[8] = 13093f;	 StarNames[8] = "Denebola";		StarInfo[8] = "Denebola is shortened from ðanab al-asad 'tail of the lion'";
        DistanceInDays[9] = 13399f;  StarNames[9] = "Arcturus";		StarInfo[9] = "Arcturus is the brightest star in the northern celestial hemisphere";
        DistanceInDays[10] = 15620f; StarNames[10] = "Capella";		StarInfo[10] = "In Hindu mythology, Capella was seen as the heart of Brahma";
        DistanceInDays[11] = 17731f; StarNames[11] = "Rasalhague";	StarInfo[11] = "The spectrum of Alpha Ophiuchi has an anomalously high level of  singly-ionized calcium";
        DistanceInDays[12] = 17899f; StarNames[12] = "Alderamin";	StarInfo[12] = "Alderamin, an Arabic name meaning 'the right arm'";
        DistanceInDays[13] = 18563f; StarNames[13] = "Castor";		StarInfo[13] = "Castor is twin brother of Pollux";
        DistanceInDays[14] = 19978f; StarNames[14] = "Caph";		StarInfo[14] = "Caph is β Cassiopeiae";
        DistanceInDays[15] = 24017f; StarNames[15] = "Hamal";		StarInfo[15] = "Hamal declination is almost exactly equal to the latitude of the Tropic of Cancer";
        DistanceInDays[16] = 24321f; StarNames[16] = "Aldebaran";	StarInfo[16] = "The name Aldebaran is Arabic and translates literally as 'the follower'";
        DistanceInDays[17] = 26990f; StarNames[17] = "Unukalhai";	StarInfo[17] = "This star is radiating about 38 times the luminosity of the Sun";
        DistanceInDays[18] = 27388f; StarNames[18] = "Alphekka";	StarInfo[18] = "The name Alphecca is Arabic, short for 'the bright of the broken'";

        StarFunnyInfo[0] = "Earth, also called the world and, less frequently, Gaia, (or Terra in some works of science fiction) is the third planet from the Sun, the densest planet in the Solar System, the largest of the Solar System's four terrestrial planets, and the only astronomical object known to accommodate life.";
        StarFunnyInfo[1] = "Having discovering the secret of hyperspace travel, humanity dedicates its first interstellar mission to a successful landing on a planet of Alpha Centauri.";
        StarFunnyInfo[2] = "Galactic archaeologist Bel Arvardin in his dissertation: Between 800–900 of the Galactic Era, the Sirius system was one of the ten most populous in the Galaxy.";
        StarFunnyInfo[3] = "The Procyon system has three inhabited planets: Osiris, an arid world whose saurian inhabitants are sentimental, rapaciously capitalistic, and capable of mind control; Isis, inhabited by a species resembling a cross 'between an elephant and a dachshund'; and Thoth, a wet planet whose natives are amoral and anarchic.";
        StarFunnyInfo[4] = "A giant planet that will plunge into the Sun 'with the mass of fifteen Jupiters' has been launched toward the Solar System by the Firstborn intelligences of the Altair system.";
        StarFunnyInfo[5] = "Vega was the capital of the Vega Province in the Galactic Empire, one of the wealthiest provinces in the entire Galaxy. Vega exported was tobacco, of notably high quality.";
        StarFunnyInfo[6] = "Fomalhaut is the home of the god Cthugha, who resembles a giant ball of fire.";
        StarFunnyInfo[7] = "Pollux has a single permanent settlement, primarily engaged in mining and refining operations.";
        StarFunnyInfo[8] = "Denebola is used for the name of three United States navy ships: USS Denebola (AD-12), USS Denebola (AF-56) and USNS Denebola (T-AKR-289).";
        StarFunnyInfo[9] = "Arcturus is a capital of the Sirius Sector in the Galactic Empire.";
        StarFunnyInfo[10] = "Capella has many moon-based colonies and space stations orbiting the brown dwarf nearest to the primary star. Despite having several starports, this system has a population of less than 10,000 people.";
        StarFunnyInfo[11] = "Tékumel is planetary system of Rasalhague  first settled by humans and several other alien species about 60,000 years in the future.";
        StarFunnyInfo[12] = "USS Alderamin (AK-116) was a United States Navy Crater class cargo ship named after the star.";
        StarFunnyInfo[13] = "Castor and Pollux are the two 'heavenly twin' stars that give the constellation Gemini (meaning twins in Latin) its name.";
        StarFunnyInfo[14] = "A receptionist at the Traveler's Inn Daswell Tippin was born at Svengay, on Caph IV. A lively little world; have you ever visited there?";
        StarFunnyInfo[15] = "The other name of Hamal, Hamul, is used for the name of United States navy ship, USS Hamul (AD-20).";
        StarFunnyInfo[16] = "Aldebaran is known by alcoholic drink of Aldebaran whiskey, as Data describe it 'It is... it is... It is green.'";
        StarFunnyInfo[17] = "Unukalhai is the home sun of the Hegléth, or Swamp Folk.";
        StarFunnyInfo[18] = "Alphecca  as the home  of the Alpheccan civilization, which rarely leaves its home system.";

    }

    /** get birthday StarName based on "days old" */
    static String getStellarBirthdayStarName(long DaysOld) {
        int LocalIndex;

//    	Arrays.sort(DistanceInDays);
//    	LocalIndex = Arrays.binarySearch(DistanceInDays, DaysOld);
        int i=0;
        do{
            if(i == NumberOfStars-1){
                if(DaysOld == DistanceInDays[i]){
                    index = i;
                    i=NumberOfStars;
                    DaysToStellarBirthday = 0;
                    return StarNames[index];
                }else {
                    DaysToStellarBirthday = DistanceInDays[index] - DaysOld;
                    i=NumberOfStars;
                    return "";
                }

            }else{
                if(DaysOld == DistanceInDays[i]){
                    index = i;
                    i=NumberOfStars;
                    DaysToStellarBirthday = 0;
                }else
                if(DaysOld > DistanceInDays[i] && DaysOld < DistanceInDays[i+1]){
                    index = i+1;
                    i=NumberOfStars;
                    DaysToStellarBirthday = DistanceInDays[index] - DaysOld;
                } else i++;

            }


        } while(i<NumberOfStars);
        return StarNames[index];

    }

    /** get birthday StarName based on "days old" */
    static String getStellarBirthdayStarFunnyInfo(long DaysOld) {
        int i=0;
        if(DaysOld<=0){
            index = 0;
        }else

            do{
                if(i == NumberOfStars-1){
                    if(DaysOld == DistanceInDays[i]){
                        index = i;
                        i=NumberOfStars;
                        DaysToStellarBirthday = 0;
                        return StarNames[index];
                    }else {
                        DaysToStellarBirthday = DistanceInDays[index] - DaysOld;
                        i=NumberOfStars;
                        return "";
                    }

                }else{
                    if(DaysOld == DistanceInDays[i]){
                        index = i;
                        i=NumberOfStars;
                        DaysToStellarBirthday = 0;
                    }else
                    if(DaysOld > DistanceInDays[i] && DaysOld < DistanceInDays[i+1]){
                        index = i+1;
                        i=NumberOfStars;
                        DaysToStellarBirthday = DistanceInDays[index] - DaysOld;
                    } else i++;

                }


            } while(i<NumberOfStars);

        return StarFunnyInfo[index];


    }
}

