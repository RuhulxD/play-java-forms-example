package database;

import models.Application;
import models.Category;
import models.PlayList;
import models.VideoBasic;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

public class AdminDAO {
    private final BasicDAO<Application> appsDao;
    private final BasicDAO<Category> catsDao;
    private final BasicDAO<PlayList> playDao;
    private final BasicDAO<VideoBasic> videoDao;

    @Inject
    public AdminDAO(JPAApi jpaApi) {
        appsDao = new BasicDAO<>(Application.class, "Application", "id", jpaApi);
        catsDao = new BasicDAO<>(Category.class, "Category", "value", jpaApi);
        playDao = new BasicDAO<>(PlayList.class, "PlayList", "id", jpaApi);
        videoDao = new BasicDAO<>(VideoBasic.class, "VideoBasic", "id", jpaApi);
    }

    public Application addApplication(Application app) throws Exception {
        return appsDao.add(app);
    }

    public List<Application> getApplications() {
        return appsDao.getAll();
    }

    public Application getApplication(String id) {
        return appsDao.getOne(id);
    }

    public boolean deleteApplication(String id) {
        return appsDao.delete(id);
    }

    //playlist
    public PlayList addPlayList(PlayList playList) {
        return playDao.add(playList);
    }
    public List<PlayList> getPlayList(String id, int start, int limit) {
        return playDao.getList(id, start, limit);
    }
    public boolean deletePlayList(String id) {
        return playDao.delete(id);
    }

    //category
    public Category addCategory(Category category) {
        System.out.println(category.toString());
        return catsDao.add(category);
    }
    public List<Category> getCategories(String id, int start, int limit) {
        return catsDao.getList("select a from Category a where name =?1", Arrays.asList(id), start, limit);
    }

    public List<Category> getCategories(String id) {
        return getCategories(id, 0, 100);
    }

    public boolean deleteCategory(String id) {
        return catsDao.delete(id);
    }
    public boolean deleteCategory(String id, String name){
        return catsDao.delete(Arrays.asList("name"), Arrays.asList(id, name));
    }

    //videoBasic
    public VideoBasic addVideo(VideoBasic videoBasic){
        return videoDao.add(videoBasic);
    }
    public List<VideoBasic> getVideos(String playlistid, int start, int limit){
        String query = "select * from VideoBasic as b left join PlayList_VideoBasic as pb on pb.videos_yURL=b.yurl" +
                "where pb.PlayList_id=?1 order by b.episode asc ";
        return videoDao.getList(query, Arrays.asList(playlistid), start, limit);
    }
    public VideoBasic getDetais(String id){
        return videoDao.getOne(id);
    }

}
