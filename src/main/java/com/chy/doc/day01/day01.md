实现了ClasspathXMlApplicationContext
总体思路：
    1.解析spring.xml配置文件，获取BeanDefinition
    2.根据BeanDefinition中的className属性，利用反射创建对象，保持在beanFactory的beanMap中

实现细节：
    1.使用BeanDefinition类来保持xml配置中的每个bean的元信息（beanName和className）
    2.使用ClassPathXmlResource来解析xml文件，提供getNext()方法获取xml文件中的每一个bean节点
    3.XmlBeanDefinitionReader使用ClassPathXmlResource获取xml文件中的每一个bean节点， 遍历所有的节点并使用registerBeanDefinition()注册bean元信息到BeanFactory
    4.创建BeanFactory，提供getBean()和registerBeanDefinition()方法，由SimpleBeanFactory去实现它
    5.SimpleBeanFactory的构造方法传入BeanDefinition，
它维护三个集合：beanDefineList beanNameList beanMap
它的registerBeanDefinition()会往beanNameList和beanDefineList中放入对应的值
它的getBean()根据BeanDefinition去懒加载bean,它真正实现getBean()方法

至此 
‘解析spring.xml配置文件，获取BeanDefinition的功能’ 由 ClassPathXmlResource和XmlBeanDefinitionReader 提供
‘根据BeanDefinition中的className属性，利用反射创建对象，保持在beanFactory的beanMap中’的功能由SimpleBeanFactory提供
最后的ClassPathXmlApplicationContext只需协调他们即可实现控制反转

    6.ClassPathXmlApplicationContext 实现BeanFactory，它的构造方法传入spring.xml文件路径，
通过new ClassPathXmlResource来获取xml中的beanNodes
通过new XmlBeanDefinitionReader(new SimpleBeanFactory) 往BeanFactory中注册beanDefinition
getBean()直接调用SimpleBeanFactory的getBean()方法
代码示例：
BeanFactory beanFactory = new SimpleBeanFactory
new XmlBeanDefinitionReader(beanFactory).loadBeanDefinitions(new ClassPathXmlResource(spring.xml))
至此已将spring.xml中所有的beanDefinition注册到SimpleBeanFactory中，SimpleBeanFactory的getBean(String beanName)方法只需反射创造对象即可。

 