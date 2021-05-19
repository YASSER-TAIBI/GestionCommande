package function;


import Controller.LoginController;
import dao.Entity.Utilisateur;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class navigation {

    
    private final String loadApp="/View/LoadAppFXML.fxml";
    private final String login="/View/Login.fxml";
    private final String home="/View/Home.fxml";
    
    
    private final String listeUtilisateur="/View/Utilisateur/ListeUtilisateur.fxml";
    private final String ajouterUtilisateur="/View/Utilisateur/AjouterUtilisateur.fxml";
    private final String modifierUtilisateur="/View/Utilisateur/ModifierUtilisateur.fxml";
    

    private final String listeFournisseur="/View/Fournisseur/ListeFournisseur.fxml";
    private final String ajouterFournisseur="/View/Fournisseur/AjouterFournisseur.fxml";
    private final String modifierFournisseur="/View/Fournisseur/ModifierFournisseur.fxml";
    private final String consultationSoldeFour="/View/Fournisseur/ConsultationSoldeFour.fxml";

    private final String listeClientMP="/View/ClientMP/ListeClientMP.fxml";
    private final String ajouterClientMP="/View/ClientMP/AjouterClientMP.fxml";
    private final String modifierClientMP="/View/ClientMP/ModifierClientMP.fxml";
    private final String consultationSoldeClient="/View/ClientMP/ConsultationSoldeClient.fxml";
    private final String soldeFinAnnee="/View/ClientMP/ConsultationSoldeFinAnnee.fxml";

    private final String validerCommande="/View/Commande/ValiderCommande.fxml";
    private final String validerCommandeRegion="/View/Commande/ValiderCommandeRegion.fxml";
    private final String validerCommandeRegionOulmes="/View/Commande/ValiderCommandeRegionOulmes.fxml";
    private final String validerCommandeOulmes="/View/Commande/ValiderCommandeOulmes.fxml";
    private final String envoyerCommande="/View/Commande/EnvoyerCommande.fxml";
    private final String envoyerCommandeOulmes="/View/Commande/EnvoyerCommandeOulmes.fxml";
    private final String saisirCommande="/View/Commande/SaisirCommande.fxml";
    private final String saisirCommandeOulmes="/View/Commande/SaisirCommandeOulmes.fxml";
    private final String modifierDetailCommande="/View/Commande/ModifierDetailCommande.fxml";
    private final String saisirCommandeRegion="/View/Commande/SaisirCommandeRegion.fxml";
    private final String saisirCommandeRegionOulmes="/View/Commande/SaisirCommandeRegionOulmes.fxml";
    private final String consultationCommande="/View/Commande/ConsultationCommande.fxml";
    private final String saisirMatierePremiere0="/View/Commande/SaisirMatierePremiere.fxml";
    private final String saisirArticleOulmes="/View/Commande/SaisirArticleOulmes.fxml";
    private final String consultationCommandeRegion="/View/Commande/ConsultationCommandeRegion.fxml";
    private final String consultationCommandeRegionOulmes="/View/Commande/ConsultationCommandeRegionOulmes.fxml";
    private final String consultationGlobalCommandeRegion="/View/Commande/ConsultationGlobalCommandeRegion.fxml";
    private final String consultationGlobalCommandeRegionOulmes="/View/Commande/ConsultationGlobalCommandeRegionOulmes.fxml";
    
    //Commande Etrangere
    private final String saisirCommandeEtrangere="/View/CommandeEtrangere/SaisirCommandeEtrangare.fxml";
    private final String ArticleEtrangere="/View/ArticleEtrangere/ListeArticleEtrangere.fxml";
    private final String FamilleArticleEtrangere="/View/ArticleEtrangere/FamilleArticle.fxml";
    private final String SousFamilleArticleEtrangere="/View/ArticleEtrangere/SousFamilleArticle.fxml";
    private final String chargementEtrangere="/View/CommandeEtrangere/ChargementEtrangere.fxml";
    private final String enPortEtrangere="/View/CommandeEtrangere/EnPortEtrangere.fxml";
    private final String prixArtFourEtrangere="/View/Referentiel/PrixArticleFour.fxml";
    private final String dossierDouane="/View/CommandeEtrangere/DossierDouane.fxml";
    private final String suiviDouane="/View/Douane/SuiviDouane.fxml";
    
    private final String reception="/View/Reception/SuiviReception.fxml";
    private final String receptionOulmes="/View/Reception/SuiviReceptionOulmes.fxml";
    
    private final String DetailFactureAvoirBonAvoirOulmes="/View/Reglement/DetailFactureAvoirBonAvoirOulmes.fxml";
    private final String SituationGlobalAvoirOulmes="/View/Reglement/SituationGlobalAvoir.fxml";
    private final String SituationGlobalAvoirClientOulmes="/View/Reglement/SituationGlobalAvoirClient.fxml";
    private final String EtatReglementOulmes="/View/Reglement/EtatReglementOulmes.fxml";
    private final String EtatReglement="/View/Reglement/EtatReglement.fxml";
    private final String reglementOulmes="/View/Reglement/ReglementOulmes.fxml";
    private final String avoirOulmes="/View/Reglement/AvoirOulmes.fxml";
    private final String EcartAvoirOulmes="/View/Reglement/EcartAvoirOulmes.fxml";
    private final String ConsultationAvoirOulmes="/View/Reglement/ConsultationAvoirOulmes.fxml";
    private final String FactureAvoirOulmes="/View/Reglement/FactureAvoirOulmes.fxml";
    private final String reglement="/View/Reglement/Reglement.fxml";
    private final String ModifierPrixReglement="/View/Reglement/ModifierPrixReglement.fxml";
    private final String consultationReglement="/View/Reglement/ConsultationReglement.fxml";
    private final String reglementReport="/View/Reglement/ReglementReport.fxml";
    private final String avanceFournisseur="/View/Reglement/AvanceFournisseur.fxml";
    private final String bonValidationAdministration="/View/Reglement/BonValidationAdministration.fxml";
    private final String prixMoyen="/View/Reglement/prixMoyen.fxml";
    private final String SaisirPromotionAccordee="/View/Reglement/SaisirPromotionAccordee.fxml";
    private final String ConsultationPromotionAccordee="/View/Reglement/ConsultationPromotionAccordee.fxml";
    private final String suiviRemiseFinPeriode="/View/Reglement/SuiviRemiseFinPeriode.fxml";
    private final String facture="/View/Reglement/Avoir.fxml"; 
    
    private final String stock="/View/Stock/ConsultationStock.fxml";
    private final String stockOulmes="/View/Stock/ConsultationStockOulmes.fxml";
    private final String stockInitialEmballage="/View/Stock/StockInitialEmballage.fxml";
    private final String situationEmballage="/View/Stock/SituationEmballage.fxml";
    private final String SaisirInventaire="/View/Stock/SaisirInvantaire.fxml";
    private final String consultationInventaire="/View/Stock/ConsultationInvantaire.fxml";
    
    private final String dimension="/View/Referentiel/Dimension.fxml";
    private final String prixDimFour="/View/Referentiel/PrixDimesionFour.fxml";
    private final String gererAuthUtilisateur="/View/Referentiel/GererAuthUtilisateur.fxml";
    private final String compte="/View/Referentiel/ListeCompte.fxml";
    private final String grammage="/View/Referentiel/ListeGrammage.fxml";
    private final String typeCartonBox="/View/Referentiel/ListeTypeCartonBox.fxml";
    private final String intervalle="/View/Referentiel/ListeIntervalle.fxml";
    private final String typeFilm="/View/Referentiel/ListeTypeFilm.fxml";
    private final String grammageFilm="/View/Referentiel/ListeGrammageFilm.fxml";
    private final String typeCarton="/View/Referentiel/ListeTypeCarton.fxml";
    private final String consultationPrixCategorie="/View/Referentiel/ConsultationPrixCategorie.fxml";
    private final String saisirReport="/View/Referentiel/SaisirReport.fxml";
    private final String modifierPrixBox= "/View/Referentiel/ModifierPrixBox.fxml";
    private final String modifierPrixCarton= "/View/Referentiel/ModifierPrixCarton.fxml";
    private final String modifierPrixFilm="/View/Referentiel/ModifierPrixFilm.fxml";
    private final String modifierPrixAdhesif="/View/Referentiel/ModifierPrixAdhesif.fxml";
    private final String modifierPrixBoxMetal="/View/Referentiel/ModifierPrixBoxMetal.fxml";
    private final String modifierPrixOulmes="/View/Referentiel/ModifierPrixOulmes.fxml";
    private final String subCategorieMp="/View/Referentiel/SubCategorieMp.fxml";
    private final String MatierePremier="/View/Referentiel/MatierePremier.fxml";
    private final String CategorieMp="/View/Referentiel/CategorieMp.fxml";
    private final String depotMagasan="/View/Referentiel/ListDepotMagasin.fxml";
    private final String articleOulmes="/View/Referentiel/ListeArticleOulmes.fxml";
    private final String historiquePrix="/View/Referentiel/HistoriquePrix.fxml";
    private final String historiquePrixOulmes="/View/Referentiel/HistoriquePrixOulmes.fxml";
    private final String historiqueAvoirPrixOulmes="/View/Referentiel/HistoriqueAvoirPrixOulmes.fxml";
    private final String delaiPaiementFour="/View/Referentiel/DelaiPaiementFour.fxml";
    private final String depot="/View/Referentiel/Depot.fxml";
    private final String chauffeur="/View/Referentiel/Chauffeur.fxml";
    private final String client="/View/Referentiel/Client.fxml";
    private final String ville="/View/Referentiel/ListeVille.fxml";
    
    private final String SaisirRetourManque="/View/RetourGratuite/SaisirRetourManque.fxml";
    private final String SaisirRetourManquePF="/View/RetourGratuite/SaisirRetourManquePF.fxml";
    private final String suiviManqueFour="/View/RetourGratuite/SuiviManqueFour.fxml";
    private final String suiviManqueFourPF="/View/RetourGratuite/SuiviManqueFourPF.fxml";
    private final String validerRetour="/View/RetourGratuite/ValiderRetour.fxml";
    private final String PaiementRetour="/View/RetourGratuite/PaiementRetour.fxml";
    private final String SuiviRetourManque= "/View/RetourGratuite/SuiviRetourManque.fxml";
    private final String SuiviRetourManquePF= "/View/RetourGratuite/SuiviRetourManquePF.fxml";
    private final String regularisationDesPrix= "/View/RetourGratuite/RegularisationDeDifferenceDePrix.fxml";
    private final String ConsultationregularisationDesPrix= "/View/RetourGratuite/ConsultationRegularisationDePrix.fxml";
    private final String ConsultationRetourManqueMp= "/View/RetourGratuite/ConsultationRetourManque.fxml";
    private final String ConsultationRetourManquePF= "/View/RetourGratuite/ConsultationRetourManquePF.fxml";
    private final String ConsultationOffres= "/View/RetourGratuite/ConsultationOffres.fxml";
    private final String suiviCommande= "/View/Livraision/SuiviCommande.fxml";
    private final String suiviCommandeOulmes= "/View/Livraision/SuiviCommandeOulmes.fxml";
    private final String recuCommande= "/View/Livraision/RecuCommande.fxml";
    private final String annulerCommande= "/View/Livraision/AnnulerCommande.fxml";
    private final String annulerCommandeOulmes= "/View/Livraision/AnnulerCommandeOulmes.fxml";
    private final String recuCommandeOulmes= "/View/Livraision/RecuCommandeOulmes.fxml";
    private final String situationGlobalCommande= "/View/Livraision/SituationGlobalCommande.fxml";
    private final String situationGlobalCommandeOulmes= "/View/Livraision/SituationGlobalCommandeOulmes.fxml";

    //iReport
    private final String iReportConsultationBonRetourGratuite = "/iReport/ConsultationBonRetour&Manque.jasper";
    private final String iReportConsultationListeOffre = "/iReport/ConsultationListeOffre.jasper";
    private final String iReportConsultationSolde = "/iReport/ConsultationSoldeFour.jasper";
    private final String iReportConsultationSoldeClientFour = "/iReport/ConsultationSoldeClientFour.jasper";
    private final String iReportRecuCommande = "/iReport/RecuCommande.jasper";
    private final String iReportRecuCommandePF = "/iReport/RecuCommandePF.jasper";
    private final String iReportBonReception ="/iReport/BonReception.jasper";
    private final String iReportBonReceptionOulmes ="/iReport/BonReceptionOulmes.jasper";
    private final String iReportBonCommande ="/iReport/BonCommande.jasper";
    private final String iReportBonCommandeAnnulerMP = "/iReport/BonCommandeAnnulerMP.jasper";
    private final String iReportBonCommandeAnnulerPF = "/iReport/BonCommandeAnnulerPF.jasper";
    private final String iReportBonCommandeOulmes = "/iReport/BonCommandeOulmes.jasper";
    private final String iReportBonRetour = "/iReport/BonRetour.jasper";
    private final String iReportBonRetourPF = "/iReport/BonRetourPF.jasper";
    private final String iReportPrixBox = "/iReport/ConsultationPrixBox.jasper";
    private final String iReportCartonBox = "/iReport/ConsultationPrixCarton.jasper";
    private final String iReportFilmBox = "/iReport/ConsultationPrixFilm.jasper";
    private final String iReportAdhesifBox = "/iReport/ConsultationPrixAdhesif.jasper";
    private final String iReportPrixBoxMetal = "/iReport/ConsultationPrixBoxMetal.jasper";
    private final String iReportPrixOulmes = "/iReport/ConsultationPrixOulmes.jasper";
    private final String iReportConsultationReglement= "/iReport/ConsultationReglement.jasper";
    private final String iReportSituationGlobalCommande= "/iReport/SituationGlobalCommande.jasper";
    private final String iReportSituationGlobalCommandeOulmes= "/iReport/SituationGlobalCommandeOulmes.jasper";
    private final String iReportConsultationStock= "/iReport/ConsultationStock.jasper";
    private final String iReportConsultationStockOulmes= "/iReport/ConsultationStockOulmes.jasper";
    private final String iReportBonReglement= "/iReport/BonReglement.jasper";
    private final String iReportMatierePremier= "/iReport/MatierePremier.jasper";
    private final String iReportConsultationRegularisationPrix= "/iReport/ConsultationRegularisationPrix.jasper";
    private final String iReportRegularisationPrix= "/iReport/RegularisationPrix.jasper";
    private final String iReportEtatReglement= "/iReport/EtatReglement.jasper";
    private final String iReportEtatReglementOulmes= "/iReport/EtatReglementOulmes.jasper";
    private final String iReportReglement= "/iReport/Reglement.jasper";
    private final String iReportReglementOulmes= "/iReport/ReglementOulmes.jasper";
    private final String iReportConsultationOffre= "/iReport/ConsultationOffre.jasper";
    private final String iReportConsultationRetourManqueMp= "/iReport/ConsultationRetourManqueMp.jasper";
    private final String iReportConsultationRetourManquePF= "/iReport/ConsultationRetourManquePF.jasper";
    private final String iReportConsultationDetailRetourManqueMp= "/iReport/ConsultationDetailRetourManqueMp.jasper";
    private final String iReportConsultationDetailRetourManquePF= "/iReport/ConsultationDetailRetourManquePF.jasper";
    private final String iReportSituationManqueFourMp= "/iReport/SituationManqueFourMP.jasper";
    private final String iReportSituationManqueFourPF= "/iReport/SituationManqueFourPF.jasper";
    private final String iReportConsultationAvanceFournisseur= "/iReport/ConsultationAvanceFournisseur.jasper";
    private final String iReportBonCommandeRegion= "/iReport/BonCommandeRegion.jasper";
    private final String iReportBonCommandeOulmesRegion= "/iReport/BonCommandeOulmesRegion.jasper";
    private final String iReportValiderCommandeRegion= "/iReport/ValiderCommandeRegion.jasper";
    private final String iReportValiderCommandeRegionOulmes= "/iReport/ValiderCommandeRegionOulmes.jasper";
    private final String iReportConsultationDimension= "/iReport/ConsultationDimension.jasper";
    private final String iReportBonFraisRetourManqueMp= "/iReport/BonFraisRetour&Manque.jasper";
    private final String iReportEcartAvoirPF= "/iReport/EcartAvoirPF.jasper";
    private final String iReportAvoirPF= "/iReport/AvoirPF.jasper";
    private final String iReportConsultationDetailAvoirRegler= "/iReport/ConsultationDetailAvoirRegler.jasper";
    private final String iReportConsultationAvoirRegler= "/iReport/ConsultationAvoirRegler.jasper";
    private final String iReportBonValidationAdministration= "/iReport/BonValidationAdministration.jasper";
    private final String iReportBonDetailValidationAdministration= "/iReport/BonDetailValidationAdministration.jasper";
    private final String iReportConsultationSoldeFinAnnee= "/iReport/ConsultationSoldeFinAnnee.jasper";
    private final String iReportConsultationAvoirGlobal= "/iReport/ConsultationAvoirGlobal.jasper";
    private final String iReportConsultationDetailAvoirGlobal= "/iReport/ConsultationDetailAvoirGlobal.jasper";
    private final String iReportConsultationBonAvoirFactureAvoir= "/iReport/ConsultationBonAvoir&FactureAvoir.jasper";
    private final String iReportSituationGlobalEmballage= "/iReport/SituationGlobalEmballage.jasper";
    private final String iReportSituationDetailEmballageMois= "/iReport/SituationDetailEmballageMois.jasper";
    private final String iReportSituationGlobalAvoirClient= "/iReport/SituationGlobalAvoirClient.jasper";
    private final String iReportConsultationDetailPromotion= "/iReport/ConsultationDetailPromotion.jasper";
    private final String iReportConsultationPromotion= "/iReport/ConsultationPromotion.jasper";
    private final String viewClient="/View/ViewCustomer.fxml"; 
    
    
    private final String dashboard="/view/Dashboard.fxml";
    private final String database="/view/Database.fxml";

    
    private final String user="/View/User.fxml";

    public Utilisateur utilisateur;

    public String getiReportRecuCommande() {
        return iReportRecuCommande;
    }
    
    public String getStock() {
        return stock;
    }

    public String getRegularisationDesPrix() {
        return regularisationDesPrix;
    }

    public String getiReportConsultationReglement() {
        return iReportConsultationReglement;
    }

    public String getConsultationOffres() {
        return ConsultationOffres;
    }

    public String getiReportConsultationSoldeFinAnnee() {
        return iReportConsultationSoldeFinAnnee;
    }

    public String getConsultationPromotionAccordee() {
        return ConsultationPromotionAccordee;
    }

    public String getConsultationInventaire() {
        return consultationInventaire;
    }

    public String getiReportConsultationDetailAvoirGlobal() {
        return iReportConsultationDetailAvoirGlobal;
    }

    public String getStockInitialEmballage() {
        return stockInitialEmballage;
    }

    public String getSaisirInventaire() {
        return SaisirInventaire;
    }

    public String getiReportSituationGlobalAvoirClient() {
        return iReportSituationGlobalAvoirClient;
    }

    public String getClient() {
        return client;
    }

    public String getSaisirPromotionAccordee() {
        return SaisirPromotionAccordee;
    }

    public String getiReportConsultationDetailPromotion() {
        return iReportConsultationDetailPromotion;
    }

    public String getiReportConsultationPromotion() {
        return iReportConsultationPromotion;
    }

    public String getiReportSituationDetailEmballageMois() {
        return iReportSituationDetailEmballageMois;
    }

    public String getSaisirRetourManquePF() {
        return SaisirRetourManquePF;
    }

    public String getiReportConsultationBonAvoirFactureAvoir() {
        return iReportConsultationBonAvoirFactureAvoir;
    }

    public String getSituationEmballage() {
        return situationEmballage;
    }

    public String getiReportSituationGlobalEmballage() {
        return iReportSituationGlobalEmballage;
    }

    public String getiReportConsultationAvoirGlobal() {
        return iReportConsultationAvoirGlobal;
    }

    public String getSuiviRemiseFinPeriode() {
        return suiviRemiseFinPeriode;
    }

    public String getBonValidationAdministration() {
        return bonValidationAdministration;
    }

    public String getDelaiPaiementFour() {
        return delaiPaiementFour;
    }

    public String getDepot() {
        return depot;
    }

    public String getiReportPrixBoxMetal() {
        return iReportPrixBoxMetal;
    }

    public String getiReportSituationManqueFourMp() {
        return iReportSituationManqueFourMp;
    }

    public String getiReportRecuCommandePF() {
        return iReportRecuCommandePF;
    }

    public String getSoldeFinAnnee() {
        return soldeFinAnnee;
    }

    public String getiReportRegularisationPrix() {
        return iReportRegularisationPrix;
    }

    public String getiReportConsultationDetailAvoirRegler() {
        return iReportConsultationDetailAvoirRegler;
    }

    public String getiReportConsultationAvoirRegler() {
        return iReportConsultationAvoirRegler;
    }

    public String getiReportAvoirPF() {
        return iReportAvoirPF;
    }

    public String getSituationGlobalCommandeOulmes() {
        return situationGlobalCommandeOulmes;
    }

    public String getiReportBonFraisRetourManqueMp() {
        return iReportBonFraisRetourManqueMp;
    }

    public String getConsultationRetourManquePF() {
        return ConsultationRetourManquePF;
    }

    public String getChauffeur() {
        return chauffeur;
    }

    public String getiReportConsultationDimension() {
        return iReportConsultationDimension;
    }

    public String getiReportValiderCommandeRegion() {
        return iReportValiderCommandeRegion;
    }

    public String getiReportBonRetour() {
        return iReportBonRetour;
    }

    public String getiReportEcartAvoirPF() {
        return iReportEcartAvoirPF;
    }

    public String getiReportBonCommandeAnnulerMP() {
        return iReportBonCommandeAnnulerMP;
    }

    public String getiReportBonCommandeAnnulerPF() {
        return iReportBonCommandeAnnulerPF;
    }

    public String getiReportBonRetourPF() {
        return iReportBonRetourPF;
    }

    public String getValiderCommandeRegionOulmes() {
        return validerCommandeRegionOulmes;
    }

    public String getiReportBonValidationAdministration() {
        return iReportBonValidationAdministration;
    }

    public String getiReportBonDetailValidationAdministration() {
        return iReportBonDetailValidationAdministration;
    }

    public String getiReportConsultationDetailRetourManqueMp() {
        return iReportConsultationDetailRetourManqueMp;
    }

    public String getSaisirCommandeRegionOulmes() {
        return saisirCommandeRegionOulmes;
    }

    public String getiReportSituationManqueFourPF() {
        return iReportSituationManqueFourPF;
    }

    public String getiReportPrixOulmes() {
        return iReportPrixOulmes;
    }

    public String getiReportConsultationDetailRetourManquePF() {
        return iReportConsultationDetailRetourManquePF;
    }

    public String getSuiviManqueFourPF() {
        return suiviManqueFourPF;
    }

    public String getSuiviRetourManquePF() {
        return SuiviRetourManquePF;
    }

    public String getiReportConsultationAvanceFournisseur() {
        return iReportConsultationAvanceFournisseur;
    }

    public String getiReportValiderCommandeRegionOulmes() {
        return iReportValiderCommandeRegionOulmes;
    }

    public String getConsultationGlobalCommandeRegion() {
        return consultationGlobalCommandeRegion;
    }

    public String getConsultationGlobalCommandeRegionOulmes() {
        return consultationGlobalCommandeRegionOulmes;
    }

    public String getiReportConsultationRetourManquePF() {
        return iReportConsultationRetourManquePF;
    }

    public String getHistoriqueAvoirPrixOulmes() {
        return historiqueAvoirPrixOulmes;
    }

    public String getHistoriquePrix() {
        return historiquePrix;
    }

    public String getiReportBonCommandeRegion() {
        return iReportBonCommandeRegion;
    }

    public String getiReportBonCommandeOulmesRegion() {
        return iReportBonCommandeOulmesRegion;
    }

    public String getHistoriquePrixOulmes() {
        return historiquePrixOulmes;
    }

    public String getAnnulerCommandeOulmes() {
        return annulerCommandeOulmes;
    }

    public String getModifierPrixReglement() {
        return ModifierPrixReglement;
    }

    public String getiReportMatierePremier() {
        return iReportMatierePremier;
    }

    public String getConsultationCommandeRegion() {
        return consultationCommandeRegion;
    }

    public String getConsultationCommandeRegionOulmes() {
        return consultationCommandeRegionOulmes;
    }

    public String getiReportSituationGlobalCommandeOulmes() {
        return iReportSituationGlobalCommandeOulmes;
    }

    public String getiReportConsultationStockOulmes() {
        return iReportConsultationStockOulmes;
    }

    public String getSuiviManqueFour() {
        return suiviManqueFour;
    }

    public String getConsultationAvoirOulmes() {
        return ConsultationAvoirOulmes;
    }

    public String getFactureAvoirOulmes() {
        return FactureAvoirOulmes;
    }

    public String getiReportConsultationListeOffre() {
        return iReportConsultationListeOffre;
    }

    public String getiReportConsultationBonRetourGratuite() {
        return iReportConsultationBonRetourGratuite;
    }

    public String getFacture() {
        return facture;
    }

    public String getReglementOulmes() {
        return reglementOulmes;
    }

    public String getiReportConsultationRetourManqueMp() {
        return iReportConsultationRetourManqueMp;
    }

    public String getAvanceFournisseur() {
        return avanceFournisseur;
    }

    public String getDetailFactureAvoirBonAvoirOulmes() {
        return DetailFactureAvoirBonAvoirOulmes;
    }

    public String getConsultationRetourManqueMp() {
        return ConsultationRetourManqueMp;
    }

    public String getSousFamilleArticleEtrangere() {
        return SousFamilleArticleEtrangere;
    }

    public String getiReportConsultationRegularisationPrix() {
        return iReportConsultationRegularisationPrix;
    }

    public String getSaisirArticleOulmes() {
        return saisirArticleOulmes;
    }

    public String getPrixMoyen() {
        return prixMoyen;
    }

    public String getModifierPrixOulmes() {
        return modifierPrixOulmes;
    }

    public String getAnnulerCommande() {
        return annulerCommande;
    }

    public String getValiderCommandeRegion() {
        return validerCommandeRegion;
    }

    public String getDepotMagasan() {
        return depotMagasan;
    }

    public String getRecuCommandeOulmes() {
        return recuCommandeOulmes;
    }

    public String getEnPortEtrangere() {
        return enPortEtrangere;
    }

    public String getiReportConsultationOffre() {
        return iReportConsultationOffre;
    }

    public String getEnvoyerCommandeOulmes() {
        return envoyerCommandeOulmes;
    }

    public String getConsultationregularisationDesPrix() {
        return ConsultationregularisationDesPrix;
    }

    public String getiReportEtatReglement() {
        return iReportEtatReglement;
    }

    public String getiReportEtatReglementOulmes() {
        return iReportEtatReglementOulmes;
    }

    public String getiReportReglement() {
        return iReportReglement;
    }

    public String getiReportReglementOulmes() {
        return iReportReglementOulmes;
    }

    public String getSituationGlobalAvoirOulmes() {
        return SituationGlobalAvoirOulmes;
    }

    public String getiReportBonReglement() {
        return iReportBonReglement;
    }

    public String getStockOulmes() {
        return stockOulmes;
    }

    public String getArticleOulmes() {
        return articleOulmes;
    }

    public String getiReportBonReceptionOulmes() {
        return iReportBonReceptionOulmes;
    }

    public String getSuiviCommandeOulmes() {
        return suiviCommandeOulmes;
    }

    public String getValiderCommandeOulmes() {
        return validerCommandeOulmes;
    }

    public String getiReportBonCommandeOulmes() {
        return iReportBonCommandeOulmes;
    }

    public String getPrixArtFourEtrangere() {
        return prixArtFourEtrangere;
    }

    public String getAvoirOulmes() {
        return avoirOulmes;
    }

    public String getSaisirCommandeEtrangere() {
        return saisirCommandeEtrangere;
    }

    public String getiReportConsultationStock() {
        return iReportConsultationStock;
    }

    public String getFamilleArticleEtrangere() {
        return FamilleArticleEtrangere;
    }

    public String getEcartAvoirOulmes() {
        return EcartAvoirOulmes;
    }

    public String getSuiviDouane() {
        return suiviDouane;
    }

    public String getSubCategorieMp() {
        return subCategorieMp;
    }

    public String getModifierPrixBoxMetal() {
        return modifierPrixBoxMetal;
    }

    public String getMatierePremier() {
        return MatierePremier;
    }

    public String getCategorieMp() {
        return CategorieMp;
    }

    public String getDossierDouane() {
        return dossierDouane;
    }

    public String getValiderRetour() {
        return validerRetour;
    }

    public String getSaisirMatierePremiere0() {
        return saisirMatierePremiere0;
    }

    public String getPaiementRetour() {
        return PaiementRetour;
    }

    public String getiReportBonCommande() {
        return iReportBonCommande;
    }

    public String getiReportConsultationSoldeClientFour() {
        return iReportConsultationSoldeClientFour;
    }

    public String getiReportBonReception() {
        return iReportBonReception;
    }

    public String getSuiviRetourManque() {
        return SuiviRetourManque;
    }

    public String getiReportSituationGlobalCommande() {
        return iReportSituationGlobalCommande;
    }

    public String getReception() {
        return reception;
    }

    public String getSituationGlobalCommande() {
        return situationGlobalCommande;
    }

    public String getiReportConsultationSolde() {
        return iReportConsultationSolde;
    }

    public String getSaisirReport() {
        return saisirReport;
    }

    public String getModifierPrixBox() {
        return modifierPrixBox;
    }

    public String getModifierPrixCarton() {
        return modifierPrixCarton;
    }

    public String getModifierPrixFilm() {
        return modifierPrixFilm;
    }

    public String getModifierPrixAdhesif() {
        return modifierPrixAdhesif;
    }

    public String getReceptionOulmes() {
        return receptionOulmes;
    }

    public String getiReportPrixBox() {
        return iReportPrixBox;
    }

    public String getiReportCartonBox() {
        return iReportCartonBox;
    }

    public String getiReportFilmBox() {
        return iReportFilmBox;
    }

    public String getiReportAdhesifBox() {
        return iReportAdhesifBox;
    }

    public String getRecuCommande() {
        return recuCommande;
    }
    
    public String getConsultationPrixCategorie() {
        return consultationPrixCategorie;
    }

    public String getTypeCarton() {
        return typeCarton;
    }

    public String getGrammageFilm() {
        return grammageFilm;
    }

     public String getTypeFilm() {
        return typeFilm;
    }

    public String getSaisirCommandeOulmes() {
        return saisirCommandeOulmes;
    }
    
    
    public String getPrixDimFour() {
        return prixDimFour;
    }

    public String getChargementEtrangere() {
        return chargementEtrangere;
    }

     
    public String getGrammage() {
        return grammage;
    }

    public String getTypeCartonBox() {
        return typeCartonBox;
    }

    public String getIntervalle() {
        return intervalle;
    }

        public String getReglementReport() {
        return reglementReport;
    }

     
    public String getConsultationSoldeClient() {
        return consultationSoldeClient;
    }
     
    public String getConsultationReglement() {
        return consultationReglement;
    }

    public String getSaisirRetourManque() {
        return SaisirRetourManque;
    }

    public String getGererAuthUtilisateur() {
        return gererAuthUtilisateur;
    }
     
    public String getConsultationSoldeFour() {
        return consultationSoldeFour;
    }
     
    public String getReglement() {
        return reglement;
        }

    public String getConsultationCommande() {
        return consultationCommande;
    }

    public String getSaisirCommandeRegion() {
        return saisirCommandeRegion;
    }

    public navigation() {
        this.utilisateur = LoginController.utilisateur;
    }

    public String getVille() {
        return ville;
    }

    public String getEtatReglementOulmes() {
        return EtatReglementOulmes;
    }

    public String getDimension() {
        return dimension;
    }
    
    
    public String getCompte() {
        return compte;
    }
    
    public String getSuiviCommande() {
        return suiviCommande;
    }
    
    public String getEnvoyerCommande() {
        return envoyerCommande;
    }
    
    public String getLoadApp() {
        return loadApp;
    }

    public String getSaisirCommande() {
        return saisirCommande;
    }

    public String getValiderCommande() {
        return validerCommande;
    }

    public String getEtatReglement() {
        return EtatReglement;
    }

    public String getArticleEtrangere() {
        return ArticleEtrangere;
    }

    public String getModifierDetailCommande() {
        return modifierDetailCommande;
    }

    public String getSituationGlobalAvoirClientOulmes() {
        return SituationGlobalAvoirClientOulmes;
    }
   

    public String getListeClientMP() {
        return listeClientMP;
    }

    public String getAjouterClientMP() {
        return ajouterClientMP;
    }

    public String getModifierClientMP() {
        return modifierClientMP;
    }
    
    public String getListeUtilisateur() {
        return listeUtilisateur;
    }

    public String getAjouterUtilisateur() {
        return ajouterUtilisateur;
    }

    public String getModifierUtilisateur() {
        return modifierUtilisateur;
    }

    public String getAjouterFournisseur() {
        return ajouterFournisseur;
    }

    public String getModifierFournisseur() {
        return modifierFournisseur;
    }
       
 //   public Image applicationIcon = new Image(getClass().getResourceAsStream("/img/icons8_Source_Code_104px_2.png"));
    
    public String getViewClient() {
        return viewClient;
    }
    public String getHome(){
        return home;
    }
    
    public String getLogin(){
        return login;
    }
    
     public String getListeFournisseur() {
        return listeFournisseur;
    }
    
    public String getDashboard(){
        return dashboard;
    }
    
    public String getDatabase(){
        return database;
    }
    
  
    
    public String getUser(){
        return user;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    
   
    
    public String generCodeCom( int a, String b){
 
      String code = a+b;
      return code;
  }
    
     public String generCode(String b ,int a){
 
      String code = b+a;
      return code;
  }
    
    public void showAlert(AlertType type, String title, String header, String text){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
    }
        
    public void harusAngka(final TextField text){
        text.setOnKeyReleased(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                if (!text.getText().matches("[0-9]*")){
                    showAlert(AlertType.WARNING, "Peringatan", null, "Hanya boleh angka !!");
                    text.setText("");
                    text.requestFocus();
                }
            }
        });
        
    }
            
    public void animationFade(Node e){
        FadeTransition x = new FadeTransition(new Duration(1000), e);
        x.setFromValue(0);
        x.setToValue(100);
        x.setCycleCount(1);
        x.setInterpolator(Interpolator.LINEAR);
        x.play();
    }

    private void Code() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
