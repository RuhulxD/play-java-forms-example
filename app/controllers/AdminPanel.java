package controllers;

import database.AdminDAO;
import models.Application;
import models.Category;
import models.PlayList;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Scala;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

public class AdminPanel extends Controller {


    private final Form<Application> appForm;
    private final Form<Category> catForm;
    private final Form<PlayList> playListForm;
    private final FormFactory factory;
    private final AdminDAO adminDAO;

    @Inject
    public AdminPanel(AdminDAO adminDAO, FormFactory formFactory) {
        this.factory = formFactory;
        this.adminDAO = adminDAO;
        this.appForm = formFactory.form(Application.class);
        this.catForm = formFactory.form(Category.class);
        this.playListForm = formFactory.form(PlayList.class);
    }

    public Result index() {
        return ok();
    }

    public Result createApplication() {
        Form<Application> boundForm = appForm.bindFromRequest();

        if (boundForm.hasErrors()) {
            play.Logger.ALogger logger = play.Logger.of(getClass());
            logger.error("errors = {}", boundForm.errors());
            Application application = new Application();
            application.apiKey="";
            application.name="";
            application.description="";
            application.id="";

            boundForm.data().put("apiKey", "");
            boundForm.data().put("name", "");
            boundForm.data().put("description", "");
            boundForm.data().put("id", "");
            return badRequest(views.html.createApplication.render(boundForm, Scala.asScala(adminDAO.getApplications()), routes.AdminPanel.createApplication()));
        } else {
            Application data = boundForm.get();
            try {
                adminDAO.addApplication(data);
                flash("info", "Apps added! App details =" + data.toString());
            }catch (Exception ex){
                flash("info", "Failed! App details =" + ex.getMessage());
            }
            return ok(views.html.createApplication.render(boundForm, Scala.asScala(adminDAO.getApplications()), routes.AdminPanel.createApplication()));
        }
    }
    public Result deleteApplication() {
        DynamicForm requestData = factory.form().bindFromRequest();
        String cat = requestData.get("name");
        boolean status =adminDAO.deleteApplication(cat);
        if(status){
            flash("info", "App deleted! Category details =" + cat);
        }else{
            flash("Error", "Apps deleted failed! Category details =" + cat);
        }
        return ok(views.html.deleteApps.render(Scala.asScala(adminDAO.getApplications())));
    }

    public Result getApplications() {
        return ok(views.html.listApplication.render(Scala.asScala(adminDAO.getApplications())));
    }

    public Result createPlayList() {
        return ok();
    }

    public Result createCategory(String application) {
        final Form<Category> boundForm = catForm.bindFromRequest();

        if (boundForm.hasErrors()) {
            play.Logger.ALogger logger = play.Logger.of(getClass());
            logger.error("errors = {}", boundForm.errors());
            return badRequest(views.html.createCategory.render(boundForm, Scala.asScala(adminDAO.getCategories(application)), application));
        } else {
            Category data = boundForm.get();
            try {
                if(data.id==null || data.id.isEmpty()){
                    data.id = Long.toString(System.currentTimeMillis());
                }
                adminDAO.addCategory(data);
                flash("info", "Categories added! Category details =" + data.toString());
            }catch (Exception ex){
                flash("info", "Failed! Category details =" + ex.getMessage());
            }
            return ok(views.html.createCategory.render(boundForm, Scala.asScala(adminDAO.getCategories(application)), application));
        }
    }
    public Result deleteCategory(String application) {
        DynamicForm requestData = factory.form().bindFromRequest();
        String cat = requestData.get("name");
        boolean status =adminDAO.deleteCategory(application, cat);
        if(status){
            flash("info", "Categories deleted! Category details =" + cat);
        }else{
            flash("Error", "Categories deleted failed! Category details =" + cat);
        }
        return ok(views.html.deleteCategory.render(Scala.asScala(adminDAO.getCategories(application)), application));
    }
    public Result getCategory() {
        return ok();
    }


}
