import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@SpringBootApplication
@Configuration
class GatewayApplication {
	@Bean
	fun routeLocator(routeLocatorBuilder: RouteLocatorBuilder): RouteLocator {
		return routeLocatorBuilder.routes()
			.route("Orders") { r ->
				r.path("/orders/**")
					.uri("http://localhost:8090/")
			}
			.route("Payment") { r ->
				r.path("/payment/**")
					.uri("http://localhost:8091/")
			}
			.route("Storage") { r ->
				r.path("/storage/**")
					.uri("http://localhost:8092/")
			}
			.build()
	}
}

fun main(args: Array<String>) {
	runApplication<GatewayApplication>(*args)
}
