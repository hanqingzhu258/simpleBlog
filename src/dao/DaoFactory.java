package dao;

public class DaoFactory {
    private static ArticalDao articalDao;
    private static UserDao userDao;
    private static CommentDao commentDao;

    static {
        articalDao = new ArticalDaoImpl();
        userDao = new UserDaoImpl();
        commentDao = new CommentDaoImpl();
    }

    public static ArticalDao getArticalDao() {
        return articalDao;
    }

    public static UserDao getUserDao() {
        return userDao;
    }

    public static CommentDao getCommentDao() {
        return commentDao;
    }
}