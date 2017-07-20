package apps.mohanad.com.miwok1;
/**
 * Created by mohanad on 05/05/17.
 * class to represent the items in the list
 */

public class Word {

    //the miwok translation
    private String miwokTranslation;
    //the english translation
    private String englishTranslation;

    //the image resource id
    private int mimageResourceId;

    //the audio resource id
    private int mAudioResourceId;

    public Word(String miwokTranslation, String englishTranslation ,int mAudioResourceId) {
        this.miwokTranslation = miwokTranslation;
        this.englishTranslation = englishTranslation;
        this.mAudioResourceId=mAudioResourceId;
    }

    public Word(String miwokTranslation, String englishTranslation , int mimageResourceId ,int mAudioResourceId) {
        //calling the two parameter constructor
        this(miwokTranslation,englishTranslation ,mAudioResourceId);
        this.mimageResourceId=mimageResourceId;
    }
    /**
     * get the misok translation
     * @return
     */
    public String getMiwokTranslation() {
        return miwokTranslation;
    }

    public void setMiwokTranslation(String miwokTranslation) {
        this.miwokTranslation = miwokTranslation;
    }

    /**
     * get the english translation
     * @return
     */
    public String getEnglishTranslation() {
        return englishTranslation;
    }

    public void setEnglishTranslation(String englishTranslation) {
        this.englishTranslation = englishTranslation;
    }

    /**
     * get the image resource id
     * @return image id
     */
    public int getImageResourceId() {
        return mimageResourceId;
    }

    /**
     * get the word audio resource id
     * @return
     */
    public int getAudioResourceId() {
        return mAudioResourceId;
    }

    @Override
    public String toString() {
        return "Word{" +
                "miwokTranslation='" + miwokTranslation + '\'' +
                ", english Translation='" + englishTranslation + '\'' +
                ", image ResourceId=" + mimageResourceId +
                ", Audio ResourceId=" + mAudioResourceId +
                '}';
    }
}

