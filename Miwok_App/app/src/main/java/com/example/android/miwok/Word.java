package com.example.android.miwok;

/**
 * Created by Aly on 4/14/2018.
 */

public class Word  {
    private String English;
    private String Miowk;
    private int ImageId=HAS_IMAGE;
    private int AudioId;
    private static final int HAS_IMAGE=-1;
    //Constructor
    public Word(String en,String mi,int audioId) {
        English=en;
        Miowk=mi;
        AudioId=audioId;
    }
    public Word(String en,String mi,int img,int audioId) {
        English=en;
        Miowk=mi;
        ImageId=img;
        AudioId=audioId;
    }
    // Getter For The English com.example.android.miwok.Word
    public String GetEnglish() {
        return English;
    }
    //Getter For The Miowk com.example.android.miwok.Word
    public String GetMiowk() {
        return Miowk;
    }
    public int GetId(){return ImageId;}
    public boolean hasImage()
    {
        return!(ImageId==HAS_IMAGE);
    }
    public int GetAudioId(){return AudioId;}
}
