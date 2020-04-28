package com.sumit.hindishayari;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;

public class SorryShayari extends AppCompatActivity {


    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    TextView shayaricount;

    ViewPager viewpager;
    MyAdapter ma;

    ImageButton previous;
    ImageButton next;
    ImageButton whatsapp;
    ImageButton copy;
    private AdView mAdView;
    
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorry_shayari);

        //for navigationnnnn
        setUpToolbar();
        navigationView =  findViewById(R.id.nav1);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        Intent home= new Intent(getBaseContext(),MainActivity.class);
                        startActivity(home);
                        break;
                    case R.id.nav_dev:
                        Intent dev= new Intent(getBaseContext(),DeveloperSumit.class);
                        startActivity(dev);
                        break;
                    case R.id.nav_share:
                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody = "Hi I have downloaded Hindi Shayari app. \nYou should try this \n" +
                                " https://play.google.com/store/apps/details?id=com.sumit.hindishayari";
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Hindi Shayari");
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(sharingIntent, "Share via"));
                        break;
                    case R.id.nav_rate:
                        try {
                            Uri uri = Uri.parse("market://details?id="+"com.sumit.hindishayari");
                            Intent goMarket = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(goMarket);
                        }catch (ActivityNotFoundException e){
                            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.sumit.hindishayari");
                            Intent goMarket = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(goMarket);
                        }
                        break;
                    case R.id.nav_exit:
                        finishAffinity();
                        System.exit(0);
                        break;
                }
                return false;
            }
        });


        //for viewpager
        viewpager= (ViewPager) findViewById(R.id.vp);
        final String[] Sorrymsg =
                {

                        "नाराज क्यूँ होते हो किस बात पे हो रूठे,\n" +
                                "अच्छा चलो ये माना तुम सच्चे हम ही झूठे,\n" +
                                "कब तक छुपाओगे तुम हमसे हो प्यार करते,\n" +
                                "गुस्से का है बहाना दिल में हो हम पे मरते।",

                        "कैसे आपको हम मनाए बस एक बार बतादो \n" +
                                "\n" +
                                "मेरी गलती मेरा कसूर मुझे याद दिला दो !!",


                        "तुम हँसते हो मुझे हँसाने के लिए,\n" +
                                "तुम रोते हो तो मुझे रुलाने के लिए,\n" +
                                "तुम एक बार रूठ कर तो देखो,\n" +
                                "मर जायेंगे तुम्हें मनाने के लिए।",

                        "आज मैंने खुद से एक वादा किया है,\n" +
                                "माफ़ी मांगूंगा तुझसे तुझे रुसवा किया है,\n" +
                                "हर मोड़ पर रहूँगा मैं तेरे साथ साथ,\n" +
                                "अनजाने में मैंने तुझको बहुत दर्द दिया है।",

                        "धड़कन बनके जो दिल में समा गए हैं,\n" +
                                "हर एक पल उनकी याद में बिताते हैं,\n" +
                                "आंसू निकल आये जब वो याद आ गए,\n" +
                                "जान निकल जाती है जब वो रूठ जाते हैं।",

                        "तुम खफा हो गए तो कोई ख़ुशी न रहेगी,\n" +
                                "तुम्हारे बिना चिरागों में रौशनी न रहेगी,\n" +
                                "क्या कहें क्या गुजरेगी दिल पर ऐ दोस्त,\n" +
                                "जिंदा तो रहेंगे लेकिन ज़िंदगी न रहेगी।",

                        "दर्द गैरों को सुनाने की ज़रूरत क्या है,\n" +
                                "अपने साथ औरों को रुलाने की ज़रूरत क्या है,\n" +
                                "वक्त यूँही कम है मोहब्बत के लिए,\n" +
                                "रूठकर वक्त गंवाने की ज़रूरत क्या है।",

                        "अहंकार दिखाके किसी रिश्ते को तोड़ने से अच्छा है,\n" +
                                "कि माफ़ी मांगकर वो रिश्ता निभाया जाये।",

                        "गुस्से में कुछ और भी हसीन लगते हो,\n" +
                                "बस यही सोच कर तुमको खफा रखा है।",

                        "कहा सुना जो भी हो माफ़ करना\n" +
                                "कुछ वादे किये ना निभाए हों तो माफ़ करना\n" +
                                "कुछ बातें जो हम दोनों के बिच हवी\n" +
                                "उन में कुछ भला बुरा हुवा हो तो माफ़ करना",

                        "कर देना माफ़ हम को दिल से अगर तोडा हो कभी दिल\n" +
                                "ज़िन्दगी किया भरोशा कल\n कफ़न में लिपटा मिले तुम को ये दोस्त तुम्हारा",

                        "इश्क की नगरी में माफ़ी नहीं किसी को भी\n" +
                                "इश्क उमर नहीं देखता बस उजाड़ देता है\n" +
                                "\n" +
                                "खता हो गई हो तो सजा भी सुना दो\n" +
                                "दिल में इतना दर्द क्यू है वजह भी बता दो\n" +
                                "देर हो गई याद करने में ज़रूर लेकिन\n" +
                                "तुमको भुला देंगे ये ख्याल दिल से निकाल दो",

                        "उससे ज़रूर माफ़ी मांगो जिससे तुम चाहते हो\n" +
                                "उसे मत छोड़ो जो तुम्हें चाहता है\n" +
                                "उस से कुछ न छुपाओ जो तुम पे ऐतबार करे",

                        "प्यास ऐसी है के पी जाओं आँखें उसकी\n" +
                                "नसीब ऐसा है के एक झलक भी नहीं\n" +
                                "बे इत्नेहा मोहब्बत कोई हम से सीखें\n" +
                                "जिसे टूट कर चाहा उसे खबर भी नहीं",

                        "ऊस ने हम से पूछा तेरी रजा किया है\n" +
                                "क्यू करते हो इतनी मोहब्बत वजा किया है\n" +
                                "कैसे बताओ उसको मेरी खता किया है\n" +
                                "जो वजा से करे मोहब्बत उस में मज़ा किया है",

                        "सांस थम जाती है पर जान नहीं जाती\n" +
                                "दर्द होता है पर आवाज़ नहीं आती\n" +
                                "अजीब लोग है इस ज़माने में\n" +
                                "कोई भूल नहीं पाता तो किसी को याद नहीं आती",

                        "कुछ अलग था कहने का अंदाज़ उनका\n" +
                                "के सुना भी कुछ नहीं कहा भी कुछ नहीं\n" +
                                "कुछ इस तरह बिखरे उनके प्यार में हम\n" +
                                "के टोटा भी कुछ नहीं और बचा भी कुछ नही",

                        "हमसे कोई खता हो जाये तो माफ़ करना,\n" +
                                "हम याद न कर पाएं तो माफ़ करना,\n" +
                                "दिल से तो हम आपको कभी भूलते नहीं,\n" +
                                "पर ये दिल ही रुक जाये तो माफ़ करना।",

                        "खता हो गयी तो फिर सज़ा सुना दो,\n" +
                                "दिल में इतना दर्द क्यूँ है वजह बता दो,\n" +
                                "देर हो गयी याद करने में जरूर,\n" +
                                "लेकिन तुमको भुला देंगे ये ख्याल मिटा दो।",

                        "बहुत उदास है कोई शख्स तेरे जाने से,\n" +
                                "हो सके तो लौट के आजा किसी बहाने से,\n" +
                                "तू लाख खफा हो पर एक बार तो देख ले,\n" +
                                "कोई बिखर गया है तेरे रूठ जाने से।",
                        "हो सकता है हमने आपको कभी रुला दिया,\n" +
                                "आपने तो दुनिया के कहने पे हमें भुला दिया,\n" +
                                "हम तो वैसे भी अकेले थे इस दुनिया में,\n" +
                                "क्या हुआ अगर आपने एहसास दिला दिया।",

                        "हम रूठे भी तो किसके भरोसे रूठें,\n" +
                                "कौन है जो आयेगा हमें मनाने के लिए,\n" +
                                "हो सकता है तरस आ भी जाये आपको,\n" +
                                "पर दिल कहाँ से लायें आपसे रूठ जाने के लिये।",


                        "दिल से तेरी याद को जुदा तो नहीं किया,\n" +
                                "रखा जो तुझे याद कुछ बुरा तो नहीं किया,\n" +
                                "हम से तू नाराज़ हैं किस लिये बता जरा,\n" +
                                "हमने कभी तुझे खफा तो नहीं किया।",

                        "अगर मै हद से गुज़र जाऊ तो मुझे माफ़ करना,\n" +
                                "तेरे दिल में उत्तर जाऊ तो मुझे माफ़ करना,\n" +
                                "रात में तुझे देख के तेरे दीदार के खातिर,\n" +
                                "पल भर जो ठहर जाऊ तो मुझे माफ़ करना!!!!",

                        "एक ज़रा सी भूल खता बन गयी,\n" +
                                "मेरी वफ़ा ही मेरी सजा बन गयी,\n" +
                                "दिल लिया और खेल कर तोड़ दिया उसने,\n" +
                                "हमारी जान गयी और उनकी अदा बन गयी.",


                        "खफा होने से पहले खता बता देना,\n" +
                                "रुलाने से पहले हँसना सिखा देना,\n" +
                                "अगर जाना हो कभी हम से दूर आप को,\n" +
                                "तो पहले बिना सांस लिए जीना सिखा देना।",

                        "न चलता है दिल पर जोर कोई,\n" +
                                "यह खुद की ही मर्जी चलाता है,\n" +
                                "करता है खटाएं कैसी कैसी,\n" +
                                "और बदले में हमें रुलाता है।",

                        "किसी के दिल में बसना कुछ बुरा तो नहीं,\n" +
                                "किसी को दिल में बसाना कोई खता तो नहीं,\n" +
                                "गुनाह हो यह ज़माने की नज़र में तो क्या,\n" +
                                "ज़माने वाले कोई खुदा तो नहीं।",


                        "मेरी हर खता पर नाराज़ न होना,\n" +
                                "अपनी प्यारी सी मुस्कान कभी न खोना,\n" +
                                "सुकून मिलता है देखकर आपकी मुस्कराहट को,\n" +
                                "मुझे मौत भी आये तो भी मत रोना!",

                        "ज़िन्दगी में याद हमारी बहूत आएगी !\n" +
                                "\n" +
                                "प्यारे सफर की हर ख़ुशी रुक जाएगी !\n" +
                                "\n" +
                                "अगर तलाश करोगे हमसे अच्छा कोई दोस्त !\n" +
                                "\n" +
                                "तो निगाहे दूर तक जाएगी फिर लौट आएगी !!",

                        "यादे होती है सताने के लिए \n" +
                                "\n" +
                                "कोई रूठता है फिर मान जाने के लिए \n" +
                                "\n" +
                                "रिश्ते बनाना कोई मुश्किल बात नहीं \n" +
                                "\n" +
                                "जान तक चली जाती है रिश्ते निभाने के लिए !!",

                        "दूरियों से फर्क नहीं पड़ता बात तो \n" +
                                "\n" +
                                "दिलो की नजदीकियों से होती है !\n" +
                                "\n" +
                                "दोस्ती तो कुछ आप जैसो से है वरना \n" +
                                "\n" +
                                "मुलाकात तो जाने कितनो से होती है !!",

                        "माना की भूल हो गई हमसे सनम !\n" +
                                "\n" +
                                "पर इस तरह ना रूठो हमसे सनम !\n" +
                                "\n" +
                                "एक बार नजरे उठा के देखो हमे !\n" +
                                "\n" +
                                "फिर दुबारा ना करेंगे ये खता ऐ सनम !!",

                        "इस कदर मेरी दोस्ती का इम्तिहान मत लीजिए !\n" +
                                "\n" +
                                "खफा हो क्यों ये तो बता दीजिए !\n" +
                                "\n" +
                                "माफ कर दो अगर हो गई कोई गलती !\n" +
                                "\n" +
                                "ऐसे रूठ करके हमे सजा मत जिजिए !!",

                        "दोस्तों में दूरियां तो आती रहती है\n" +
                                "\n" +
                                "फिर भी दोस्ती दिलो को मिलाती रहती है\n" +
                                "\n" +
                                "वो दोस्ती ही क्या जो नाराज़ ना हो पर\n" +
                                "\n" +
                                "सच्ची दोस्ती दोस्तों को मना लेती है !!",


                        "भूल से कोई भूल हुई तो भूल समझ कर भूल जाना !\n" +
                                "\n" +
                                "अरे भूलना सिर्फ भूल को भूल से हमे न भूल जाना !!",


                        "बहुत उदास है कोई तेरे जाने से !\n" +
                                "\n" +
                                "हो सके तो लौट आ किसी  बहाने से  !\n" +
                                "\n" +
                                "तू लाख खफा सही मगर एक बार तो देख !\n" +
                                "\n" +
                                "कोई टूट सा गया है तेरे दूर जाने से !!",


                        "खता हो गयी तो फिर सज़ा सुना दो,\n" +
                                "दिल में इतना दर्द क्यूँ है वजह बता दो,\n" +
                                "देर हो गयी याद करने में जरूर,\n" +
                                "लेकिन तुमको भुला देंगे ये ख्याल दिल से मिटा दो।",

                        "आज मैंने खुद से एक वादा किया है,\n" +
                                "माफ़ी मांगूंगा तुझसे तुझे रुसवा किया है,\n" +
                                "हर मोड़ पर रहूँगा मैं तेरे साथ साथ,\n" +
                                "अनजाने में मैंने तुझको बहुत दर्द दिया है।",

                        "आज मैंने खुद से एक वादा किया है,\n" +
                                "माफ़ी मांगूंगा तुझसे तुझे रुसवा किया है,\n" +
                                "हर मोड़ पर रहूँगा मैं तेरे साथ साथ,\n" +
                                "अनजाने में मैंने तुझको बहुत दर्द दिया है।",

                        "धड़कन बनके जो दिल में समा गए हैं,\n" +
                                "हर एक पल उनकी याद में बिताते हैं,\n" +
                                "आंसू निकल आये जब वो याद आ गए,\n" +
                                "जान निकल जाती है जब वो रूठ जाते हैं।",

                        "तुम खफा हो गए तो कोई ख़ुशी न रहेगी,\n" +
                                "तुम्हारे बिना चिरागों में रौशनी न रहेगी,\n" +
                                "क्या कहें क्या गुजरेगी दिल पर ऐ दोस्त,\n" +
                                "जिंदा तो रहेंगे लेकिन ज़िंदगी न रहेगी।",

                        "रिश्तों में दूरियां तो आती-जाती रहती हैं,\n" +
                                "फिर भी दोस्ती दिलो को मिला देती है,\n" +
                                "वो दोस्ती ही क्या जिसमे नाराजगी न हो,\n" +
                                "पर सच्ची दोस्ती दोस्तों को मना ही लेती है।",

                        "खफा होने से पहले खता बता देना,\n" +
                                "रुलाने से पहले हँसना सिखा देना,\n" +
                                "अगर जाना हो कभी हम से दूर आप को,\n" +
                                "तो पहले बिना सांस लिए जीना सिखा देना।",

                        "हमसे कोई खता हो जाये तो माफ़ करना\n" +
                                "हम याद न कर पाएं तो माफ़ करना\n" +
                                "दिल से तो हम आपको कभी भूलते नहीं\n" +
                                "पर ये दिल ही रुक जाये तो माफ़ करना…",

                        "किसी के दिल में बसना कुछ बुरा तो नहीं,\n" +
                                "किसी को दिल में बसाना कोई खता तो नहीं,\n" +
                                "गुनाह हो यह ज़माने की नज़र में तो क्या,\n" +
                                "ज़माने वाले कोई खुदा तो नहीं।",

                        "आज मैंने खुद से एक वादा किया है,\n" +
                                "माफ़ी मांगूंगा तुझसे तुझे रुसवा किया है,\n" +
                                "हर मोड़ पर रहूँगा मैं तेरे साथ साथ,\n" +
                                "अनजाने में मैंने तुझको बहुत दर्द दिया है।",

                        "खता हो गयी तो फिर सज़ा सुना दो,\n" +
                                "दिल में इतना दर्द क्यूँ है वजह बता दो,\n" +
                                "देर हो गयी याद करने में जरूर,\n" +
                                "लेकिन तुमको भुला देंगे ये ख्याल मिटा दो।",

                        "दिल से तेरी याद को जुदा तो नहीं किया,\n" +
                                "रखा जो तुझे याद कुछ बुरा तो नहीं किया,\n" +
                                "हम से तू नाराज़ हैं किस लिये बता जरा,\n" +
                                "हमने कभी तुझे खफा तो नहीं किया।",

                        "हम रूठे भी तो किसके भरोसे रूठें,\n" +
                                "कौन है जो आयेगा हमें मनाने के लिए,\n" +
                                "हो सकता है तरस आ भी जाये आपको,\n" +
                                "पर दिल कहाँ से लायें आपसे रूठ जाने के लिये।",

                        "नाराज क्यूँ होते हो किस बात पे हो रूठे,\n" +
                                "अच्छा चलो ये माना तुम सच्चे हम ही झूठे,\n" +
                                "कब तक छुपाओगे तुम हमसे हो प्यार करते,\n" +
                                "गुस्से का है बहाना दिल में हो हम पे मरते।",

                        "हो सकता है हमने आपको कभी रुला दिया,\n" +
                                "आपने तो दुनिया के कहने पे हमें भुला दिया,\n" +
                                "हम तो वैसे भी अकेले थे इस दुनिया में,\n" +
                                "क्या हुआ अगर आपने एहसास दिला दिया।",

                        "हमसे कोई खता हो जाये तो माफ़ करना,\n" +
                                "हम याद न कर पाएं तो माफ़ करना,\n" +
                                "दिल से तो हम आपको कभी भूलते नहीं,\n" +
                                "पर ये दिल ही रुक जाये तो माफ़ करना।",

                        "बहुत उदास है कोई शख्स तेरे जाने से,\n" +
                                "हो सके तो लौट के आजा किसी बहाने से,\n" +
                                "तू लाख खफा हो पर एक बार तो देख ले,\n" +
                                "कोई बिखर गया है तेरे रूठ जाने से।",

                        "धड़कन बनके जो दिल में समा गए हैं,\n" +
                                "हर एक पल उनकी याद में बिताते हैं,\n" +
                                "आंसू निकल आये जब वो याद आ गए,\n" +
                                "जान निकल जाती है जब वो रूठ जाते हैं।",

                        "कहा सुना जो भी हो माफ़ करना\n" +
                                "कुछ वादे किये ना निभाए हों तो माफ़ करना\n" +
                                "कुछ बातें जो हम दोनों के बिच हवी\n" +
                                "उन में कुछ भला बुरा हुवा हो तो माफ़ करना",

                        "दर्द किया होता है बतायेंगे एक रोज़\n" +
                                "प्यार की गजल सुनायेंगे किसी रोज़\n" +
                                "थी उनकी जिद की में जाऊ उनको मानाने\n" +
                                "मुजको ये वेहम था वो बुलायेंगे किसी रोज़",

                        "होंटों से दुआ के लिए जसने नहीं होती\n" +
                                "अब इससे जायदा तेरी खुवासिश नहीं होती\n" +
                                "है प्यार का सहेर यहाँ बदल नहीं आती\n" +
                                "अगर बदल भी आ जाये तो बारिश नहीं होती",

                        "रिश्तों में दूरियां तो आती-जाती रहती हैं,\n" +
                                "फिर भी दोस्ती दिलो को मिला देती है,\n" +
                                "वो दोस्ती ही क्या जिसमे नाराजगी न हो,\n" +
                                "पर सच्ची दोस्ती दोस्तों को मना ही लेती है।",

                        "दोस्ती में दोस्त, दोस्त का ख़ुदा होता है,\n" +
                                "महसूस तब होता है जब वो जुदा होता है।",

                        "आसमान से तोड़ कर सितारा दिया है,\n" +
                                "आलम-ए-तन्हाई में एक शरारा दिया है,\n" +
                                "मेरी किस्मत भी नाज़ करती है मुझपे,\n" +
                                "खुदा ने दोस्त ही इतना प्यारा दिया है।",

                        "हम जब भी आपकी दुनिया से जायेंगे,\n" +
                                "इतनी खुशियाँ और अपनापन दे जायेंगे,\n" +
                                "कि जब भी याद करोगे इस पागल दोस्त को,\n" +
                                "हँसती आँखों से आँसू निकल आयेंगे।",

                        "दोस्ती नाम है सुख-दुःख की कहानी का,\n" +
                                "दोस्ती राज है सदा ही मुस्कुराने का,\n" +
                                "ये कोई पल भर की जान-पहचान नहीं है,\n" +
                                "दोस्ती वादा है उम्र भर साथ निभाने का।",

                        "सॉरी कहने का मतलब है,\n" +
                                "कि आपके लिए दिल में प्यार है,\n" +
                                "अब जल्दी से हमे माफ़ कर दो ऐ सनम,\n" +
                                "सुना है आप बहुत समझदार हैं।",

                        "बहुत उदास है कोई शख्स तेरे जाने से,\n" +
                                "हो सके तो लौट के आजा किसी बहाने से,\n" +
                                "तू लाख खफा हो पर एक बार तो देख ले,\n" +
                                "कोई बिखर गया है तेरे रूठ जाने से।",

                        "आज मैंने खुद से एक वादा किया है,\n" +
                                "माफ़ी मंगुगा तुझसे तुझे रुसवा किया है,\n" +
                                "हर मोड़ पर रहूँगा मैं तेरे साथ साथ,\n" +
                                "अनजाने में मैंने तुझको बहुत दर्द दिया है।",

                        "तुम दुआ हो मेरी सदा के लिए,\n" +
                                "मैं जिंदा हूँ तुम्हारी दुआ के लिए,\n" +
                                "कर लेना लाख शिकवे हमसे,\n" +
                                "मगर कभी खफा न होना खुदा के लिए।",

                        "गलती तो हो गयी है,\n" +
                                "अब क्या मार डालोगे?\n" +
                                "माफ़ भी कर दो ऐ सनम,\n" +
                                "ये गलफहमी कब तक पालोगे?",


                        "हर वक़्त तुमको याद करता हूँ,\n" +
                                "हद से ज्यादा तुम्हे प्यार करता हूँ,\n" +
                                "क्यों तुम मुझसे खफा बैठे हो,\n" +
                                "मैं एक तुम्हीं पर तो मरता हूँ।",

                        "अगर मै हद से गुज़र जाऊ तो मुझे माफ़ करना\n" +
                                "तेरे दिल में उत्तर जाऊ तो मुझे माफ़ करना\n" +
                                "रात में तुझे देख के तेरे दीदार के खातिर\n" +
                                "पल भर जो ठहर जाऊ तो मुझे माफ़ करना,",

                        "दिल से तेरी याद को जुदा तो नहीं किया\n" +
                                "रखा जो तुझे याद कुछ बुरा तो नहीं किया\n" +
                                "हम से तू नाराज़ हैं किस लिये बता जरा\n" +
                                "हमने कभी तुझे खफा तो नहीं किया",

                        "आदतें अलग हैं मेरी दुनिया वालों से,\n" +
                                "दोस्त कम रखता हूँ पर लाजवाब रखता हूँ।",

                        "सच्चे दोस्त हमें कभी गिरने नहीं देते,\n" +
                                "न किसी कि नजरों मे न किसी के कदमों में।",

                        "बहुत उदास है कोई शख्स तेरे जाने से,\n" +
                                "हो सके तो लौट के आजा किसी बहाने से,\n" +
                                "तू लाख खफा हो पर एक बार तो देख ले,\n" +
                                "कोई बिखर गया है तेरे रूठ जाने से।",

                        "नाराज क्यूँ होते हो किस बात पे हो रूठे,\n" +
                                "अच्छा चलो ये माना तुम सच्चे हम ही झूठे,\n" +
                                "कब तक छुपाओगे तुम हमसे हो प्यार करते,\n" +
                                "गुस्से का है बहाना दिल में हो हम पे मरते।",

                        "खता हो गयी तो फिर सज़ा सुना दो,\n" +
                                "दिल में इतना दर्द क्यूँ है वजह बता दो,\n" +
                                "देर हो गयी याद करने में जरूर,\n" +
                                "लेकिन तुमको भुला देंगे ये ख्याल मिटा दो,\n" +
                                "सॉरी डार्लिंग…!!",

                        "देखा है आज मुझे भी गुस्से की नज़र से,\n" +
                                "मालूम नहीं आज वो किस-किस से लड़े है|\n" +
                                "न तेरी शान कम होती न रुतबा ही घटा होता,\n" +
                                "जो गुस्से में कहा तुमने वही हँस के कहा होता|",

                        "आज एक वादा करते हैं तुमसे,\n" +
                                "मेरे लिए अब कोई नहीं ज्यादा है तुमसे,\n" +
                                "माफ़ कर दो जो रुसवा किया तुमको,\n" +
                                "गलती हमारी थी जो खुद से जुड़ा किया तुमको,\n" +
                                "i am sorry dear….!!",

                        "भूल से भूल को भुला दो जरा,\n" +
                                "आशिक आपके है गले से लगा लो जरा,\n" +
                                "फिर ना करेंगे नराज़ आपको,\n" +
                                "अब तो थोडा मुस्कुरा दो जरा,\n" +
                                "सॉरी बेबी….!!",

                        "दिल से तेरी याद को जुदा तो नहीं किया,\n" +
                                "रखा जो तुझे याद कुछ बुरा तो नहीं किया,\n" +
                                "हम से तू नाराज़ हैं किस लिये बता जरा,\n" +
                                "हमने कभी तुझे खफा तो नहीं किया….!!",

                        "बेशक इजाज़त है आपको रूठ जाने की,\n" +
                                "हमे तो आदत है मनाने की,\n" +
                                "अगर आप नहीं माने तो,\n" +
                                "हम परेशान कर देंगे मिस कॉल से सताने की,\n" +
                                "चलो अब रूठना-मनाना बंद करो||",

                        "हमारा तुमपे कोई हक तो नहीं है,\n" +
                                "फिर भी ये ज़रूर कहना चाहेंगे,\n" +
                                "हमारी ज़िन्दगी तुम मांगलो,\n" +
                                "मगर प्लीज उदास मत रहा करो,\n" +
                                "कीप स्मिलिंग…!!",

                        "पलभर में टूट जाये वो कसम नहीं,\n" +
                                "तुम्हे भूल जाये वो हम नहीं,\n" +
                                "तुम रूठी रहो इस बात में दम नहीं,\n" +
                                "तुम मनाने से न मनो इतने बुरे हम भी नहीं,\n" +
                                "i am sorry डिअर….!!",

                        "जान है मुझे ज़िन्दगी से प्यारी,\n" +
                                "जान के लिए कर दू कुर्बान कुछ भी,\n" +
                                "जान के लिये तोड़ दू यारी तुम्हारी,\n" +
                                "अब तो मान जाओ मनाने से,\n" +
                                "क्यूंकि तुम्ही हो जान हमारी\n" +
                                "i am sorry darling….!",

                        "हो सकता है हमने आपको कभी रुला दिया,\n" +
                                "आपने तो दुनिया के कहने पे हमें भुला दिया,\n" +
                                "हम तो वैसे भी अकेले थे इस दुनिया में,\n" +
                                "क्या हुआ अगर आपने एहसास दिला दिया",

                        "जान है मुझे ज़िन्दगी से प्यारी,\n" +
                                "जान के लिए कर दू कुर्बान कुछ भी,\n" +
                                "जान के लिये तोड़ दू यारी तुम्हारी,\n" +
                                "अब तो मान जाओ मनाने से,\n" +
                                "क्यूंकि तुम्ही हो जान हमारी",

                        "कोई गिला कोई शिकवा ना रहे आपसे;\n" +
                                "यह आरज़ू है कि सिलसिला रहे आपसे;\n" +
                                "बस इस बात की बड़ी उम्मीद है आपसे;\n" +
                                "खफा ना होना अगर हम खफा रहें आपसे।",

                        "जब देखा तुझे पहली बार\n" +
                                "सब कुछ गुलाबी लगने लगा\n" +
                                "शायद हमें भी प्यार हो गया होगा\n" +
                                "ऐसा एहसास होने लगा",

                        "उन से दूर होने के बाद\n" +
                                "दर्द का एहसास पता चला\n" +
                                "जब वो बहुत दूर चले गए\n" +
                                "मौत का रास्ता पता चला",

                        "बहुत रोये वो हमारे पास आके\n" +
                                "जब एहसास हुआ अपनी गलती का\n" +
                                "चुप तो करा देते हम,\n" +
                                "अगर चहरे पे हमारे कफन ना होता",

                        "दर्द का एहसास दे गए\n" +
                                "हम से बहुत दूर चले गए\n" +
                                "मेरी जिंदगी से चले गए\n" +
                                "अब वो बस तस्वीरो में रह गए",

                        "कुछ दर्द ऐसे होते हैं\n" +
                                "जिन्हें दवा और दुआ,\n" +
                                "दोनों भी काम नहीं आते",

                        "जख़्म भरे नहीं अब पुरे\n" +
                                "वो और दर्द देने लगे\n" +
                                "पूछा हमने के क्या गलती हैं तो\n" +
                                "मुझे देखकर मुस्कुराने लगे\n" +
                                "और हम उनकी मुस्कराहट के लिए\n" +
                                "ख़ुशी से दर्द सहने लगे",


                        "मेरे जीने का एहसास हो तुम\n" +
                                "मेरी सांस हो तुम\n" +
                                "इस दिल का अरमान हो तुम\n" +
                                "मेरे लिए जिंदगी जीना का मक़सद हो तुम",

                        "खामोशी में भी एक एहसास हैं\n" +
                                "मेरे दर्द के पीछे भी कई राज़ हैं",

                        "जो लोग न तो अपनी गलतियों से सीखते हैं,\n" +
                                "और न दूसरों की गलतियों से सीखते हैं,\n" +
                                "वैसे लोग बर्बाद हो जाते हैं।",

                        "हर रिश्ता बहुत खास सा होता है\n" +
                                "फिजूल हवाओं में साँस सा होता है\n" +
                                "जिसे अपना माना, छोड़कर चले गए\n" +
                                "आज उनकी कीमत का एहसास सा होता है",

                        "शीशा और रिशता दोनों हि बङे नाज़ुक होते हैं\n" +
                                "दोनों में सिर्फ एक ही फर्क है,\n" +
                                "शीशा गलती से टूट जाता है\n" +
                                "और रिशता गलतफहमियों से",


                        "जख़्म इतना गहरा हैं इज़हार क्या करें।\n" +
                                "हम ख़ुद निशां बन गये ओरो का क्या करें।\n" +
                                "मर गए हम मगर खुली रही आँखे हमरी।\n" +
                                "क्योंकि हमारी आँखों को उनका इंतेज़ार हैं।",



                        "बहुत उदास है कोई शख्स तेरे जाने से,\n" +
                                "हो सके तो लौट के आजा किसी बहाने से,\n" +
                                "तू लाख खफा हो पर एक बार तो देख ले,\n" +
                                "कोई बिखर गया है तेरे रूठ जाने से।"







                };


        ma= new MyAdapter(Sorrymsg,SorryShayari.this);
        viewpager.setAdapter(ma);






        //for showing count of shayari on top....by sumit.....
        shayaricount = findViewById(R.id.count);
        //default set to 1
        shayaricount.setText(String.format("Sorry Shayari : %d/%d", viewpager.getCurrentItem() + 1, Sorrymsg.length));
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @SuppressLint("DefaultLocale")
            @Override
            public void onPageSelected(int position) {

                shayaricount.setText(String.format("Sorry Shayari : %d/%d", position+1 , Sorrymsg.length));
            }


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        // next button
        next=(ImageButton)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos=viewpager.getCurrentItem();
                if ((pos+1)==ma.getCount()){
                    viewpager.setCurrentItem(0);
                }else{
                    viewpager.setCurrentItem(pos+1);
                }
            }
        });



        //previous button
        previous=(ImageButton)findViewById(R.id.previous);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos=viewpager.getCurrentItem();
                if (pos==0){
                    viewpager.setCurrentItem(ma.getCount());
                }else{
                    viewpager.setCurrentItem(pos-1);
                }
            }
        });


        //for whatsapp
        whatsapp = findViewById(R.id.whatsapp);
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, Sorrymsg[viewpager.getCurrentItem()]);
                try {
                    startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getBaseContext(),"Whatsapp Not Installed",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //for copy
        copy = findViewById(R.id.copy);
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("msg", Sorrymsg[viewpager.getCurrentItem()]);
                assert clipboard != null;
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getBaseContext(),"Copied To Clipboard !",Toast.LENGTH_SHORT).show();
            }
        });

        // for addddddd
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



    }
    //for navigation bar
    private void setUpToolbar(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer1);
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
    
}
