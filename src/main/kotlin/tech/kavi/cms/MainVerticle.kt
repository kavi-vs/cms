package tech.kavi.cms

import tech.kavi.cms.module.HttpVerticle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import
import tech.kavi.cms.dao.BookDao
import tech.kavi.cms.dao.UserDao
import tech.kavi.cms.entity.Book
import tech.kavi.cms.entity.User
import tech.kavi.vs.mybatis.MybatisDataSourceBean
import tech.kavi.vs.mybatis.observableAsync
import tech.kavi.vs.mybatis.singleAsync
import tech.kavi.vs.web.HandlerRequestAnnotationBeanName
import tech.kavi.vs.web.LauncherVerticle

@Import(BeanConfig::class, MybatisDataSourceBean::class)
@ComponentScan(nameGenerator = HandlerRequestAnnotationBeanName::class)
class MainVerticle : LauncherVerticle() {


    @Autowired
    private lateinit var userDao: UserDao

    @Autowired
    private lateinit var bookDao: BookDao

    @Autowired
    private lateinit var httpVerticle: HttpVerticle

    @Throws(Exception::class)
    override fun start() {
        super.start()
        this.initData()

        vertx.deployVerticle(httpVerticle)
    }

    /**
     * 初始化插入数据例子
     * */
    private fun initData() {

        /**
         * 基于Single模式异步线程插入数据
         * */
        singleAsync { userDao.add(User(name=System.currentTimeMillis().toString())) }.subscribe {
            println("成功插入USER")
        }

        /**
         * 基于Observable模式异步线程插入数据
         * */
        observableAsync { bookDao.add(Book(name=System.currentTimeMillis().toString())) }.subscribe {
            println("成功插入BOOK")
        }
    }

    companion object {
        @JvmStatic
        fun main(args:Array<String>) {
            // 初始化类
            launcher(MainVerticle::class.java)
        }
    }
}
