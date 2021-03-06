(ns clj-kondo.impl.parser
  {:no-doc true}
  (:require
   [clojure.string :as str]
   [clj-kondo.impl.utils :refer [parse-string-all]]))

(defn parse-string [s]
  (let [input (-> s
                  ;; workaround for https://github.com/xsc/rewrite-clj/issues/75
                  (str/replace "##Inf" "::Inf")
                  (str/replace "##-Inf" "::-Inf")
                  (str/replace "##NaN" "::NaN")
                  ;; workaround for https://github.com/borkdude/clj-kondo/issues/11
                  #_(str/replace #_"#:a{#::a {:a b}}"
                               #"#(::?)(.*?)\{" (fn [[_ colons name]]
                                                  (str "#_" colons name "{"))))
        parsed (parse-string-all input)]
    parsed))

;;;; Scratch

(comment
  (parse-string "#::{:a 1}")
  )
