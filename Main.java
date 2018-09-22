import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    public static void main(String[] args) { launch(args); }

    public void start(Stage primaryStage){

        primaryStage.setWidth(800);
        primaryStage.setHeight(400);
        primaryStage.setTitle("Dessin Artistique");
        primaryStage.setResizable(false);

        //FONDS
        Rectangle fondJour = new Rectangle(0,0,400,400);
        fondJour.setFill(Color.LIGHTGRAY);

        Rectangle fondNuit = new Rectangle(400,0,400,400);
        fondNuit.setFill(Color.BLACK);

        Group fonds = new Group( fondJour, fondNuit);


        //ECRITURES
        Text jour = new Text(150, 320, "Jour");
        jour.setFont(new Font("Carlito", 40));

        Text nuit = new Text(555, 320, "Nuit");
        nuit.setFill(Color.WHITE);
        nuit.setFont(new Font("Carlito", 43));

        Group ecritures = new Group(jour, nuit);

        //ASTRES

            //Soleil
        Circle soleil = new Circle(325, 70, 30);
        soleil.setFill(Color.YELLOW);
        soleil.setStroke(Color.LIGHTGRAY);
        soleil.setStrokeWidth(6);
        FillTransition trans = new FillTransition(
                Duration.seconds(3), soleil);
        trans.setToValue(Color.ORANGE);
        trans.setCycleCount(Timeline.INDEFINITE);
        trans.setAutoReverse(true);
        trans.play();


        Line rayon1 = new Line(255,70, 395, 70 );
        rayon1.setStroke(Color.YELLOW);
        Line rayon2 = new Line(325,1, 325, 139 );
        rayon2.setStroke(Color.YELLOW);
        Line rayon3 = new Line(285,30, 365, 110 );
        rayon3.setStroke(Color.YELLOW);
        Line rayon4 = new Line(285,110, 365, 30 );
        rayon4.setStroke(Color.YELLOW);

        Group rayons = new Group(rayon1,rayon2,rayon3,rayon4);
        RotateTransition rotation = new RotateTransition(
                Duration.seconds(7), rayons);
        rotation.setByAngle(360);
        rotation.setInterpolator(Interpolator.LINEAR);
        rotation.setCycleCount(Timeline.INDEFINITE);
        rotation.play();

            //Lune
        Circle lune = new Circle(740, 65, 30);
        Stop[] stops = new Stop[]{
        new Stop(0, Color.WHITE),
        new Stop(1, Color.BLACK),};
        LinearGradient gradient = new LinearGradient(
                0,0,       // Coordonnées du départ
                1,1,        // Coordonnées de la fin
                true,       // Utiliser coordonnées de 0 à 1
                CycleMethod.NO_CYCLE, // Comment afficher les répétitions
                stops);
        lune.setFill(gradient);

            //Etoiles

        Polygon star1 = creationEtoile();
        Polygon star2 = creationEtoile();
        Polygon star3 = creationEtoile();
        Polygon star4 = creationEtoile();

        star1.setTranslateX(510);star1.setTranslateY(75); //2
        star2.setTranslateX(450);star2.setTranslateY(25); //1
        star3.setTranslateX(575);star3.setTranslateY(55); //3
        star4.setTranslateX(650);star4.setTranslateY(80); //4

        star1.setScaleX(0.9);star1.setScaleY(0.9);
        star2.setScaleX(0.5);star2.setScaleY(0.5);
        star3.setScaleX(0.7);star3.setScaleY(0.7);
        star4.setScaleX(0.6);star4.setScaleY(0.6);



        FadeTransition fade1 = new FadeTransition(Duration.seconds(2),star1);
        fade1.setFromValue(1.0);
        fade1.setToValue(0.0);
        fade1.setCycleCount(Timeline.INDEFINITE);
        fade1.setAutoReverse(true);
        fade1.play();

        FadeTransition fade2 = new FadeTransition(Duration.seconds(2.5),star2);
        fade2.setFromValue(1.0);
        fade2.setToValue(0.0);
        fade2.setCycleCount(Timeline.INDEFINITE);
        fade2.setAutoReverse(true);
        fade2.play();

        FadeTransition fade3 = new FadeTransition(Duration.seconds(1.7),star3);
        fade3.setFromValue(1.0);
        fade3.setToValue(0.0);
        fade3.setCycleCount(Timeline.INDEFINITE);
        fade3.setAutoReverse(true);
        fade3.play();

        FadeTransition fade4 = new FadeTransition(Duration.seconds(1.7),star4);
        fade4.setFromValue(1.0);
        fade4.setToValue(0.0);
        fade4.setCycleCount(Timeline.INDEFINITE);
        fade4.setAutoReverse(true);
        fade4.play();

        Group etoiles = new Group(star1, star2, star3, star4);


        Group astres = new Group(rayons, soleil, lune, etoiles);

        //MAISON

        Group maisonJour = creationMaison();
        Group maisonNuit = creationMaison();

        maisonNuit.setTranslateX(410);

        DropShadow ombre = new DropShadow(6,-9,9,Color.DARKGRAY);
        maisonJour.setEffect(ombre);

        //OISEAUX
        Group oiseau1 = creationOiseau();
        Group oiseau2 = creationOiseau();

        oiseau1.setTranslateY(40);
        oiseau1.setTranslateX(-25);
        oiseau1.setScaleX(0.9);
        oiseau1.setScaleY(0.9);

        oiseau2.setTranslateX(100);
        oiseau2.setTranslateY(-20);
        oiseau2.setScaleX(0.9);
        oiseau2.setScaleY(0.9);

        Group oiseaux = new Group(oiseau1, oiseau2);

        //FINAL
        Group root = new Group(fonds, ecritures, astres, maisonJour, maisonNuit, oiseaux);

        primaryStage.setScene(new Scene((root )));
        primaryStage.show();

    }

    private Rectangle creationFenetre(){
        Rectangle fenetre = new Rectangle(205,212, 20,13);
        fenetre.setFill(Color.AQUA);
        fenetre.setStroke(Color.DARKGRAY);
        fenetre.setStrokeWidth(1.2);
        return fenetre;
    }

    private Group creationMaison(){
        Rectangle fondation = new Rectangle(135,200,100,60);
        fondation.setFill(Color.BEIGE);

        Polygon toit = new Polygon(130,200,185,150,240,200);
        toit.setFill(Color.BROWN);

        Rectangle porte = new Rectangle(145,225, 25,35);
        porte.setFill(Color.SADDLEBROWN);

        Circle poigne = new Circle(163,245, 4);
        poigne.setFill(Color.GREENYELLOW);

        Rectangle fene1 = creationFenetre();
        Rectangle fene2 = creationFenetre();
        Rectangle fene3 = creationFenetre();
        Rectangle fene4 = creationFenetre();

        fene2.setX(185); fene2.setY(212);
        fene3.setX(185); fene3.setY(225);
        fene4.setX(205); fene4.setY(225);
        Group fenetres = new Group(fene1, fene2, fene3, fene4);

        Group maison = new Group(fondation, toit, porte, poigne, fenetres );
        return maison;
    }

    private Polygon creationEtoile(){

        Polygon etoile = new Polygon(
                0,15,
                18,15,
                23,1,
                28,15,
                44,15,
                31,24,
                35,40,
                23,30,
                8,40,
                14,24
                );

        etoile.setFill(Color.YELLOW);

        return  etoile;
    }

    private Group creationOiseau(){

        QuadCurve aile1 = new QuadCurve(50,100,80,50,100,80 );
        QuadCurve aile2 = new QuadCurve(100,80,120,50,150,100 );

        aile1.setFill(Color.LIGHTGRAY);
        aile1.setStroke(Color.BLACK);
        aile2.setFill(Color.LIGHTGRAY);
        aile2.setStroke(Color.BLACK);

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);

        KeyValue kv1 = new KeyValue(aile1.startYProperty(),aile1.getStartY()-20, Interpolator.LINEAR);
        KeyValue kv2 = new KeyValue(aile2.endYProperty(),aile2.getEndY()-20, Interpolator.LINEAR);

        KeyValue kv5 = new KeyValue(aile1.startXProperty(),aile1.getStartX()+5, Interpolator.LINEAR);
        KeyValue kv6 = new KeyValue(aile2.endXProperty(),aile2.getEndX()+5, Interpolator.LINEAR);

        KeyValue kv3 = new KeyValue(aile2.startYProperty(),aile2.getStartY()+10, Interpolator.LINEAR);
        KeyValue kv4 = new KeyValue(aile1.endYProperty(),aile1.getEndY()+10, Interpolator.LINEAR);




        KeyFrame kf1 = new KeyFrame(Duration.seconds(1), kv1, kv2, kv3, kv4, kv5, kv6);

        timeline.getKeyFrames().addAll(kf1);
        timeline.play();

        Group oiseau = new Group(aile1, aile2);
        return oiseau;

    }







}
