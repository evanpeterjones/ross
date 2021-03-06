(ns ross.core
  (:require [clj-http.client :as client]
            [clojure.xml :as xml]
            [clojure.zip :as zip])
  (:gen-class))

(def rss-feed "https://www.digitalpodcast.com/feeds/go_rss?feed_id=26779")

(def feed-data (client/get rss-feed))

(def feed-str (:body feed-data))

(defn zip-str [s]
  (let [res (xml/parse s)]
    (if true nil))
  (zip/xml-zip))

(defn -main [& args]
  (time (println "Hello World!")))

(def xml "<?xml version=\"1.0\" encoding=\"UTF-8\"?>
<rss version=\"2.0\"
    xmlns:googleplay=\"http://www.google.com/schemas/play-podcasts/1.0\"
    xmlns:itunes=\"http://www.itunes.com/dtds/podcast-1.0.dtd\">
  <channel>
    <title>Dafna's Zebra Podcast</title>
    <googleplay:owner>dafna@example.com</googleplay:owner>
    <googleplay:author>Dafna</googleplay:author>
    <description>A pet-owner's guide to the popular striped equine.</description>
    <googleplay:image href=\"https://www.example.com/podcasts/dafnas-zebras/img/dafna-zebra-pod-logo.jpg\"/>
    <language>en-us</language>
    <link>https://www.example.com/podcasts/dafnas-zebras/</link>
    <item>
      <title>Top 10 myths about caring for a zebra</title>
      <description>Here are the top 10 misunderstandings about the care, feeding, and breeding of these lovable striped animals.</description>
      <pubDate>Tue, 14 Mar 2017 12:00:00 GMT</pubDate>
      <enclosure url=\"https://www.example.com/podcasts/dafnas-zebras/audio/toptenmyths.mp3\"
                 type=\"audio/mpeg\" length=\"34216300\"/>
      <itunes:duration>30:00</itunes:duration>
      <guid isPermaLink=\"false\">dzpodtop10</guid>
    </item>
    <item>
      <title>Keeping those stripes neat and clean</title>
      <description>Keeping your zebra clean is time consuming, but worth the effort.</description>
      <pubDate>Fri, 24 Feb 2017 12:00:00 GMT</pubDate>
      <enclosure url=\"https://www.example.com/podcasts/dafnas-zebras/audio/cleanstripes.mp3\"
                 type=\"audio/mpeg\" length=\"26004388\"/>
      <itunes:duration>22:48</itunes:duration>
      <guid>dzpodclean</guid>
    </item>
  </channel>
</rss>")
