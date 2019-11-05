// This #include statement was automatically added by the Particle IDE.
#include <InternetButton.h>



InternetButton button = InternetButton();

    String x = " ";
    String y = " ";
    String z = " ";

void setup() {
    button.begin();

    Particle.function("score",displayScore);
    Particle.function("answer1",showCorrectOrIncorrect1);
    Particle.function("answer2",showCorrectOrIncorrect2);

    for(int i=0;i<3;i++){
        button.allLedsOn(20,0,0);
        delay(250);
        button.allLedsOff();
        delay(250);
    }

}

void loop() {





    //Event 1;
    if(button.buttonOn(4)){
        Particle.publish("playerChoice","B",60,PUBLIC);
        delay(60);
    }
    if(button.buttonOn(2)){
        Particle.publish("playerChoice","A",60,PUBLIC);
        delay(60);
    }
    //Event 2;
    if(button.buttonOn(3)){




         int xValue = button.readX();
         String sx(xValue);
         //How about in the y direction?
         int yValue = button.readY();
         String sy(yValue);
         //And the z!
         int zValue = button.readZ();
         String sz(zValue);

        //  sprintf(y,"%d",yValue);
        //  sprintf(x,"%d",xValue);
        //  sprintf(z,"%d",zValue);
         Particle.publish("X",sx,60,PUBLIC);
         Particle.publish("Y",sy,60,PUBLIC);
         Particle.publish("Z",sz,60,PUBLIC);



    }

}

int displayScore(String cmd){
    //reset the displayed score
    button.allLedsOff();


    //turn on the specified number of lights
    int score = cmd.toInt();

    if(score <= 0 || score >11){
        return -1;
    }

    for(int i=0;i<=score;i++){
    button.ledOn(i,255,0,0);
    }
    return 1;
}

int showCorrectOrIncorrect1(String cmd){
    if(cmd=="square1"){
        for(int i=0;i<=10;i++){
        button.ledOn((4*i)+1,255,0,0);
        }
        // button.allLedsOn(0,255,0);
        delay(4000);
        button.allLedsOff();
    }
    else if (cmd == "triangle1"){
        for(int i=0;i<=10;i++){
        button.ledOn((3*i)+1,0,255,0);
        }
        // button.allLedsOn(0,255,0);
        delay(4000);
        button.allLedsOff();
        // button.allLedsOn(255,0,0);
        // delay(2000);
    }
    else{
        return -1;
    }
    return 1;
}
int showCorrectOrIncorrect2(String cmd){
    if(cmd=="triangle2"){
        for(int i=0;i<=10;i++){
        button.ledOn((4*i)+1,0,255,0);
        }
        // button.allLedsOn(0,255,0);
        delay(4000);
        button.allLedsOff();
    }
    else if (cmd == "square2"){
        for(int i=0;i<=10;i++){
        button.ledOn((3*i)+1,255,0,0);
        }
        // button.allLedsOn(0,255,0);
        delay(4000);
        button.allLedsOff();
        // button.allLedsOn(255,0,0);
        // delay(2000);
    }
    else{
        return -1;
    }
    return 1;
}
