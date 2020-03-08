package io.hosuaby.elasticsearch;

import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.cluster.metadata.IndexNameExpressionResolver;
import org.elasticsearch.cluster.node.DiscoveryNodes;
import org.elasticsearch.common.settings.ClusterSettings;
import org.elasticsearch.common.settings.IndexScopedSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.settings.SettingsFilter;
import org.elasticsearch.plugins.ActionPlugin;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.rest.BaseRestHandler;
import org.elasticsearch.rest.BytesRestResponse;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestHandler;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.RestStatus;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * Plugin that handles request GET /hello and answers with "Hello world!".
 * Must implement {@link ActionPlugin} to be able handle REST requests.
 */
public class HelloWorldPlugin extends Plugin implements ActionPlugin {
    @Override
    public List<RestHandler> getRestHandlers(
            Settings settings,
            RestController restController,
            ClusterSettings clusterSettings,
            IndexScopedSettings indexScopedSettings,
            SettingsFilter settingsFilter,
            IndexNameExpressionResolver indexNameExpressionResolver,
            Supplier<DiscoveryNodes> nodesInCluster) {
        HelloHandler helloHandler = new HelloHandler(restController);
        return Collections.singletonList(helloHandler);
    }

    /**
     * Implementation of REST handler.
     */
    static class HelloHandler extends BaseRestHandler {
        HelloHandler(RestController restController) {
            super();
            restController.registerHandler(RestRequest.Method.GET, "/hello", this);
        }

        @Override
        public String getName() {
            return "Hello world";
        }

        @Override
        protected RestChannelConsumer prepareRequest(RestRequest request, NodeClient client) {
            return restChannel -> restChannel
                    .sendResponse(
                            new BytesRestResponse(RestStatus.OK, "Hello world!"));
        }
    }
}
