public class RedisBackedCacheTest {
    @Rule
    public GenericContainer redis = new GenericContainer("redis:3.0.6")
                                       .withExposedPorts(6379);
         String redisUrl = redis.getContainerIpAddres() + redis.getMappedPort(6379);

         PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:9.6.2")
       .withUsername(POSTGRES_USERNAME)
       .withPassword(POSTGRES_PASSWORD);

        public RedisBackedCacheTest(GenericContainer redis, PostgreSQLContainer postgreSQLContainer) {
          this.redis = redis;
          this.postgreSQLContainer = postgreSQLContainer;
        }
}
