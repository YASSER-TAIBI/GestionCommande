/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * 
 */
public class Constantes {

    
    
    /*MESSAGE D'ERREUR */
   public static boolean DeveloppingMode=true;

    
    public static String  CHAMP_OBLIGATOIRE= "Champ Obligatoire";
    
    /*Etat Commande*/
    public static String ETAT_COMMANDE_LANCE="Lancé";
    public static String ETAT_COMMANDE_VALIDEE="Validée"; 
    public static String ETAT_COMMANDE_EN_ROUTE="En Route"; 
    public static String ETAT_COMMANDE_EN_ATTENTE="En Attente";
    public static String ETAT_COMMANDE_ENCOURS="En Cours"; 
    public static String ETAT_COMMANDE_EN_PORT="En Port"; 
    public static String ETAT_COMMANDE_RECU="Reçu";
    public static String ETAT_COMMANDE_LANCE_REGION="Région Lancé";
    public static String ETAT_COMMANDE_VALIDEE_REGION="Région Validée";
    public static String ETAT_COMMANDE_SUPPRIMER="Supprimer"; 
    
    /*Type Commande*/
    public static String COMMANDE_INTERNE="Interne"; 
    public static String COMMANDE_INTERNE_ART="Interne Art";
    public static String COMMANDE_EXTERNE="Externe"; 
    
    /*MP Adhesif*/
    public static String MP_901="MP_901"; 
    public static String MP_907="MP_907"; 
    public static String MP_908="MP_908"; 
    public static String MP_757="MP_757"; 
    public static String MP_836="MP_836"; 
    
    /*Compteur Sequenceur*/
    public static String DIMENSION="DIM ";
    public static String FOURNISSEUR="FOUR ";
    public static String CLIENT="CLT ";
    public static String RECEPTION_CODE="RCP";
    public static String RECEPTION_ETRANGERE="RCP_ETG ";
    public static String REGLEMENT_CODE="RGL";
    public static String RECEPTION_OULMES_CODE="RCP_OLM";
    public static String REGLEMENT_OULMES_CODE="RGL_OLM";
    public static String OFFRE_CODE="OFR";
    public static String REGULARISATION_CODE="RGLS";
    public static String REPORT_CODE="RPT";
    public static String COMMANDE="COM";
    public static String COMMANDE_OULMES="COM_OLM";
    public static String COMMANDE_REGION="COMR ";
    public static String COMMANDE_ETRANGERE="COM_ETG ";
    public static String RETOUR_CODE="RTE ";
    public static String FACTURE_AVOIR_CODE="FACT ";
    public static String BON_AVOIR_CODE="BON_AVR ";
    public static String MANQUE_CODE="MNQ ";
    public static String MATIERE_PREMIER="MP_";
    
    /*Etat Suppetion Detail Commande*/
    public static String  ETAT_SUPPRIMER="1";
    public static String  ETAT_AFFICHAGE="0";
    
    /*Etat d'Affichage Form*/
    public static final int POUR_RECHERCHER=0;
    public static final int POUR_AJOUTER=0;
    public static final int POUR_MOUDIFIER=1;
    
    /*Formule de Calcule*/
    public static BigDecimal TAUX_TVA_20 = new BigDecimal (0.2);
   
    /*Etat de Reglement*/
    public static String ETAT_A_REGLE="A Régler";
    public static String ETAT_NON_REGLE="Non Régler";
    public static String ETAT_REGLE="Régler";
    public static String ETAT_OFFRE="Offre";
    
    public static String EN_VRAC="EN VRAC";
    public static String CONDITIONNÉ="CONDITIONNÉ";
    
    public static String ETAT_NON_PAIEMENT="Non Paiement";
    
    public static String ETAT_A_PAYER="A Payer";
    
    public static String PIECE="PIECE";
    public static String PACK="PACK";
    
    public static String CLIENT_SAHARA="SAHARA PACKING";
    
    public static String ETAT_BL_AV="BL N° ";
    public static String ETAT_BL_FACTUR="Facture N° ";
    
    public static String ETAT_BL_AVOIR="BL Avoir";
    public static String ETAT_FACTURE_AVOIR="FAC Avoir";
    public static String ETAT_REGLEMENT_AVOIR="RGL Avoir";
    
    public static String FACTURE_AVOIR_N="Facture Avoir N° ";
    public static String SUR_BON_AVOIR_N=" sur Bon d'Avoir N° "; 
    public static String SUR_FACTURE_N=" sur Facture N° ";
    
    public static String REGULARISATION_AUGMENTATION_FACTURE ="Regularisation d'augmentation des prix sur Facture N° ";
    public static String REGULARISATION_DIMINUTION_FACTURE ="Regularisation de diminution des prix sur Facture N° ";
    
    public static String DESIGNATION_RECEPTION_BON_LIVRAISION="Reception Bon Livraison N° ";
    public static String DESIGNATION_COMMANDE_N="Commande N° ";
    
    public static String MANQUE_RETOUR_N="N° ";
    public static String SUR_DATE_ECHEANCE="sur Date d'Echéance ";
    public static String SUR_COMMANDE_N="sur Commande N° ";
    public static String RECEPTION_BON_LIVRAISON="_Reception Bon Livraison N°  ";
    
    public static String TRAITE_N="Traite N° ";
    
    public static String RETOUR_N="Retour N° ";
    public static String MANQUE_N="Manque N° ";
    public static String OFFRE="Offre ";
    
    public static String THE_MKARKAB="THE MKARKAB";
    public static String THE_CHAARA="THE CHAARA";
    
    public static String REGLEMENT_SUR_BL="Reglement sur BL N° ";
    public static String REGLEMENT_SUR_REPORT="Reglement sur Report Année ";
    public static String REPORT_SUR_ANNEE ="Report sur Année ";
    public static String RETOUR_SUR_COMMANDE_N="Retour sur Commande N° ";
    public static String MANQUE_SUR_COMMANDE_N="Manque sur Commande N° ";
    
    public static String VALIDATION_SUR_BON_RETOUR_N="Validation sur Bon de Retour N° ";
    public static String VALIDATION_SUR_BON_MANQUE_N="Validation sur Bon de Manque N° ";

    public static String AVOIR="Avoir N° ";
    public static String FACTURE_N="Facture N° ";
    public static String PAIEMENT_NORMAL="sur Paiement Normal: ";
    public static String PAIEMENT="sur Paiement: ";
    
    public static String AVOIR_OULMES_SUR_N_ART="Avoir Oulmes sur Article N° ";
    
    public static String RETOUR= "Retour";
    public static String RETOUR_EN_ATT_REGLEMENT= "En Attente Reglement";
    public static String RETOUR_VALIDER= "Retour Valider";
    public static String MANQUE_VALIDER= "Manque Valider";
    public static String RETOUR_PAYE= "Retour Payé";
    public static String GRATUITE= "Gratuité";
    public static String MANQUE= "Manque";
    
    public static String SORTIE_DE_STOCK= "Sortie de Stock";
    
    public static String BON_RETOUR= "Bon Retour";
    public static String BON_GRATUITE= "Bon Gratuité";

    public static String DIMINUTION= "Diminution";
    public static String AUGMENTATION= "Augmentation";
    
    public static String EN_STOCK_OUI= "Oui";
    public static String EN_STOCK_NON= "Non";
    public static String SANS= "Sans"; 
    
    public static String ETAT_RTR= "RTR";
    public static String ETAT_MNQ= "MNQ";
    public static String ETAT_BL= "BL";
    public static String ETAT_OULMES= "OLS";
    public static String ETAT_FACTURE= "FACT";
    
    
    
    public static String RECEPTION_TYPE= "Reception";
    public static String USINE_TYPE= "Usine";
    
    public static String ETAT_CMR= "CMR";
    public static String ETAT_CMNR= "CMNR";
    
    public static String ETAT_PAYEE="Payée";
    public static String ETAT_ANNULE="Annulée";
    
    public static String ETAT_F= "F ";
    public static String ETAT_D= "D ";
    
    public static String SIEGE= "Siege";
    public static String REGION= "Region";
    public static String DEPOT= "AGADIR";
    
    public static String CODE_DEPOT_SIEGE= "SIEGE";
    public static String MAGASIN_CODE_TYPE_MP= "MP";
    public static String MATIERE_PREMIERE_SERVICE_PRODUCTION= "MP_SERV";
    public static String CATEGORIE_MAGASIN = "STK";
    
    public static String FIX= "Fix";
    public static String VARIABLE= "Variable";
    
    public static String MAROCAINE= "Marocaine";
    public static String ETRANGER= "Etranger";
    
    public static String CHEQUE= "Chèque";
    public static String ESPECE="Espèce";
    public static String ORDER_DE_VIREMENT="Ordre de Virement";
    public static String TRAITE="Traite";
    
    public static String REPORT="Report";
    
    public static String CODE_INTERVALLE="I0"; 
    public static String CODE_SPECIAL_FOUR= "F ";
    public static String CODE_SPECIAL_DEPOT= "D ";
    public static String CODE_DIMENSION="DIM 21";
    public static String CODE_MP="XXX";
    
    public static String CLIENT_MARJANE="Marjane";
    public static String CLIENT_MINURSO="Minurso";
    
    public static String FOURNISSEUR_FLEXIMAT= "FLEXIMAT S.A";
    public static String FOURNISSEUR_OULMES= "OULMES ETAT";
    
    public static String BOX= "BOX"; 
    public static String CARTON= "CARTON";
    public static String FILM_NORMAL= "FILM NORMAL";
    public static String FILM_GOLD= "FILM GOLD";
    public static String ADHESIF= "ADHESIF";
    public static String BOX_METAL= "BOX METAL";
    public static String CELLOPHANE="CELLOPHANE";
    
    public static String ANNEES_2016= "2016";
    public static String ANNEES_2017= "2017";
    public static String ANNEES_2018= "2018";
    
    public static String DATE= "30-11-0002";
    
    public static String MESSAGE_ALERT_VALIDER_QTE_LIVRAISON ="Voulez-vous vraiment continuer le traitement ?";
    public static String MESSAGE_ALERT_FERMER_SESSION ="Voulez-vous vraiment fermer la session ?";
    public static String MESSAGE_ALERT_QUITTER_APP ="Voulez-vous vraiment quitter l'application ?";
   
    public static String COMMANDE_VALIDER = "Commande Valideé avec Succès";
    public static String COMMANDE_RECU = "Commande Reçu avec Succès";
    public static String COMMANDE_RETOUR_EN_ATTENTE ="Traitement En Attente";
    public static String COMMANDE_RETOUR ="Commande Retour Valideé avec Succès";
    public static String COMMANDE_RETOUR_REGLE ="Commande Retour Réglé avec Succès";
    public static String COMMANDE_GRATUITE ="Commande Gratuite Valideé";
    
    
    /*CODE MENU*/
    public static final String COD_MENU_COMMANDE = "commandeMenu";
    public static final String COD_MENU_COMMANDE_REGION = "commandeRegionMenu";
    public static final String COD_MENU_SAISIR_COMMANDE_REGION = "saisirCommandeRegionMenu";
    public static final String COD_MENU_VALIDER_COMMANDE_REGION = "validerCommandeRegionMenu";
    public static final String COD_MENU_COMMANDE_SIEGE = "commandeSiegeMenu";
    public static final String COD_MENU_SAISIR_COMMANDE = "saisirCommandeMenu";
    public static final String COD_MENU_VALIDER_COMMANDE = "validerCommandeMenu";
    public static final String COD_MENU_ENVOYER_COMMANDE = "envoyerCommandeMenu";

    public static final String COD_MENU_LIVRAISON = "livraisonMenu";
    public static final String COD_MENU_SUIVI_LIVRAISON = "suiviLivraisonMenu";
    public static final String COD_MENU_SITUATION_GLOBAL_COMMANDE = "situationGlobalCommandeMenu";
    public static final String COD_MENU_Recu_COMMANDE = "recuCommandeMenu";
    
    public static final String COD_MENU_RECEPTION = "receptionMenu";
    public static final String COD_MENU_SUIVI_RECEPTION = "suiviReceptionMenu";

    public static final String COD_MENU_RETOUR_GRATUITE = "retourGratuiteMenu";
    public static final String COD_MENU_SUIVI_RETOUR_GRATUITE = "suiviRetourGratuiteMenu";
    public static final String COD_MENU_VALIDER_RETOUR = "validerRetourMenu";
    public static final String COD_MENU_PAIEMANT_RETOUR = "PaiemantRetourMenu";
    public static final String COD_MENU_CONSULTATION_RETOUR_GRATUITE = "consultationBonRetourGratuiteMenu";
    
    public static final String COD_MENU_REGLEMENT = "reglementMenu";
    public static final String COD_MENU_SUIVI_ETAT_REGLEMENT = "suiviEtatReglementMenu";
    public static final String COD_MENU_SUIVI_REGLEMENT = "suiviReglementMenu";
    public static final String COD_MENU_REPORT_REGLEMENT = "reglementReportMenu";
    public static final String COD_MENU_CONSULTATION_REGLEMENT = "consultationReglementMenu";

    public static final String COD_MENU_STOCK = "stockMenu";
    public static final String COD_MENU_CONSULTATION_STOCK = "consultationStockMenu";

    public static final String COD_MENU_CLIENT = "clientMenu";
    public static final String COD_MENU_AJOUTER_CLIENT = "ajouterClientMenu";
    public static final String COD_MENU_CONSULTATION_SOLDE_CLIENT = "consultationSoldeClientMenu";
    
    public static final String COD_MENU_FOURNISSEUR = "fournisseurMenu";
    public static final String COD_MENU_AJOUTER_FOURNISSEUR = "ajouterFournisseurMenu";
    public static final String COD_MENU_CONSULTATION_SOLDE_FOURNISSEUR = "consultationSoldeFourMenu";
    
    
    public static final String COD_MENU_REFERENTIEL = "referentielMenu";
    public static final String COD_MENU_MATERIAL_BOX = "materielBoxMenu";
    public static final String COD_MENU_MATERIAL_CARTON = "materielCartonMenu";
    public static final String COD_MENU_MATERIAL_FILM = "materielFilmMenu";
    public static final String COD_MENU_DIMENSION = "dimensionMenu";
    public static final String COD_MENU_GRAMMAGE = "gammageMenu";
    public static final String COD_MENU_GRAMMAGE_FILM = "gammageFilmMenu";
    public static final String COD_MENU_TYPE_CARTON_BOX = "typeCartonBoxMenu";
    public static final String COD_MENU_TYPE_CARTON = "typeCartonMenu";
    public static final String COD_MENU_TYPE_FILM = "typeFilmMenu";
    public static final String COD_MENU_INTERVALLE = "intervalleMenu";
    public static final String COD_MENU_VILLE = "villeMenu";
    public static final String COD_MENU_COMPTE = "compteMenu";
    public static final String COD_MENU_SAISIR_REPORT = "saisirReportMenu";
    public static final String COD_MENU_MATIERE_PREMIERE = "matierePremiereMenu";
    public static final String COD_MENU_SAISIR_MATIERE_PREMIERE = "saisirMatierePremiereMenu";
    public static final String COD_MENU_CATEGORIE_MATIERE_PREMIERE = "categorieMatierePremiereMenu";
    public static final String COD_MENU_SUB_CATEGORIE_MATIERE_PREMIERE = "subCategorieMatierePremiereMenu";
    
    public static final String COD_MENU_PARAMETRE = "parametreMenu";
    public static final String COD_MENU_PRIX_DIMENSION = "prixDimensionFourMenu";
    public static final String COD_MENU_CONSULTATION_PRIX_CATEGORIE = "consultationPrixCategorieMenu";
    public static final String COD_MENU_AJOUTER_UTILISATEUR = "ajouteUtilisateurMenu";
    public static final String COD_MENU_GERER_AUTHORISATION = "gererAuthorisationMenu";

    public static String COMBO_INTERNE = "Interne";
    public static String COMBO_EXTERNE = "Externe";
    public static String MESSAGE_ALERT_AUCUN_TRAITEMENT = "Aucun Traitement n'a été réalisée";
    public static String COMMANDE_DEJA_TRAITEE ="La commande a déjà été traitée ! ";
    public static String SELECTIONNER_UNE_LIGNE ="attention vous devez sélectionner une ligne";
    public static String TRAITEMENT_BIEN_ENREGESTRE ="Traitement Bien Enregestre SVP Imprimer l'Etat !!";
    public static String TRAITEMENT_ENREGESTRE ="Traitement Bien Enregestre ";
    public static String SELECTION_LIGNE_MODIFIER ="Il faut Séléctionner la ligne à modifier SVP !!";
    public static String SELECTION_ERREUR = "Erreur Seléction";
    public static String ERREUR_DATE ="Erreur Date";
    public static String ERREUR ="Erreur";
    public static String SELECTION_MONTANT = "Tapez le Montant !!";
    public static String SELECTION_FOURNISSEUR = "Sélectionner un Fournisseur";
    public static String VERIFIER_QTE_LIVREE = "Veuillez s'il vous plaît vérifier que la Qte Livrée de set Article n'est pas vide !!";
    public static String VERIFIER_QTE = "Veuillez s'il vous plaît vérifier la Qte que Vous avez Saisi !!";
    public static String VERIFIER_CODE_ARTICLE = "Veuillez s'il vous plaît vérifier le Code Article que Vous avez Saisi !!";
    public static String VERIFIER_BON_GRATUITE_BON_ROUTEUR = "Vous devez sélectionner (Bon Retour/Gratuité/Manque ou Etat de Bon). les champs Vide !!";
    public static String VERIFICATION_SELECTION_LIGNE ="Il faut selectionner une ligne SVP !!";
    public static String VERIFIER_CHAMP_MONTANT ="Vous devez Remplir le champ Montant !!";
    public static String REMPLIR_CHAMPS = "Vous devez Remplir Tout les champs SVP !!";
    public static String REMPLIR_CHAMPS_DATE = "Vous devez Remplir les Dates SVP !!";
    public static String MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT ="Vous devez Vérifier la Date Fin et Supérieur du Date Debut SVP!!";
    public static String REMPLIR_COCHE = "Vous devez coché une cellule pour règle SVP!!";
    public static String VERIFIER_NUM_COMMANDE = "Veuillez s'il vous plaît vérifier votre NUM COMMANDE que Vous avez Saisi !!";
    public static String VERIFIER_BON_RETOUR_BON_GRATUITE_MANQUE = "Vous devez cocher un Type (Retour/ Gratuité/ Manque) SVP !!";
    public static String VERIFIER_FIX_VARIABLE ="Vous devez cochez un Type (Fix/Variable) SVP !!";
    public static String VERIFIER_COORDONEES_EXISTE ="Veuillez s'il vous plaît vérifier votre traitement les coordonnées et deja existe !!";
    public static String VERIFIER_AJOUTER_BOX_CARTON_FILMGOLD_FILMNORMAL_ADHESIF ="Les Prix que vous pouvez Ajouter jusqu'a maintenant sont: (Prix Box, Prix Carton, Prix Cellophane, Prix Adhesif) SVP !!";
    public static String VERIFICATION_DATE_SAISIE ="Vous devez saisir une Date Correcte par exemple: 2018/05/10 SVP!!";
    public static String VERIFIER_PRIX_SAISIE ="Le Prix que Vous avez Saisi pour ce Fournisseur n'existe pas !!";
    public static String VERIFIER_N_RETOUR="Veuillez s'il vous plaît vérifier votre NUM RETOUR !!";
    public static String VERIFIER_FOURNISSEUR_CLIENT="Veuillez s'il vous plaît vérifier que les champs (Client/Fournisseur) n'ont pas Vide !!"; 
    public static String SUPRIMER_ENREGISTREMENT = "l'Enregistrement à été Supprimée avec succés";
    public static String REGLER_ENREGISTREMENT = "l'Enregistrement à été Reglée avec succés";
    public static String OFFRE_ENREGISTREMENT = "Les Données sont bien Enregistré";
    public static String MODIFIER_ENREGISTREMENT = "l'Enregistrement à été Modifiée avec succés";
    public static String AJOUTER_ENREGISTREMENT = "l'Enregistrement à été Ajoutée avec succés";
    public static String CONFIRMATION_ENREGISTREMENT = "La Modification réussie avec succès, Vous devez Valider la Commande Directement";
    public static String VERIFIER_QTE_PRIX = "Veuillez s'il vous plaît vérifier Les Champs Qte, Prix !!";
    public static String VERIFIER_FILTRE = "Veuillez s'il vous plaît vérifier votre Filtration (Sub Categorie, Catigorie, MP)!!";
    public static String VERIFIER_MONTANT_COMPTE = "Veuillez s'il vous plaît vérifier le montant du compte n'existe pas!!";    
    public static String VERIFIER_CODE_LIBELLE = "Veuillez s'il vous plaît vérifier que les champs (Code Article/Libelle) n'ont pas Vide !!";
    public static String VERIFIER_TABLE = "Veuillez s'il vous plaît vérifier La Table de Traitement des valeurs vides !!";
}
  