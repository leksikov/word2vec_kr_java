# Java implementation of Word2Vec based on Korean text

This project crawl the dataset from Korean movie script description and review web service.

 https://movie.naver.com
 
 This dataset selected because movies have variety of genres which describe different types of situatons.
 Given the dataset word embedding are implemented using word2vec language model.
 
 ## Programming flow
 
 Crawling and data storage -> Text processing (removing stop words) -> Dataset one-hot vector encoding 
 -> Neural Network module -> training NN -> resulted embedding -> statistics and visualization
