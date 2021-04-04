(ns ross.handler
  (:require
   [reitit.ring :as reitit-ring]
   [clojure.data.json :as json]
;   [cljs-personal-web.middleware :refer [middleware]]
   [hiccup.page :refer [include-js include-css html5]]
   [config.core :refer [env]]))

(def mount-target
  [:div#app])

(defn head []
  [:head
   [:title "Evan Jones"]
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1"}]
   [:link {:rel "icon" :href "favicon.png"}]
   ;;[:link {:rel "stylesheet" :href "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"}]
   [:link {:href "https://fonts.googleapis.com/css?family=Work+Sans:300,400,600,700" :rel "stylesheet"}]
   (include-css (if (env :dev) "/css/site.css" "/css/site.min.css"))])

(defn input-rss [url]
  [:p url])

(defn loading-page [rss]
  (html5
   (head)
   [:body {:class "body-container"}
    mount-target
    [:h1 "ROSS"]
    (input-rss rss)
    (include-js "/js/app.js")]))

(def rqs (atom []))

(defn index-handler
  [{{{:keys [rss]} :query} :parameters}]
  (swap! rqs conj (str "test: " rss))
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (loading-page rss)})

(def app
  (reitit-ring/ring-handler
   (reitit-ring/router
    [["/" {:get {:handler index-handler}}
      ["/:rss" {:get {:handler index-handler
                      :path-params {:rss string?}}}]]])
   (reitit-ring/routes
    (reitit-ring/create-resource-handler {:path "/" :root "/public"})
    (reitit-ring/create-default-handler))))

