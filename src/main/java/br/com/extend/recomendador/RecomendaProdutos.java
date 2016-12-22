package br.com.extend.recomendador;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class RecomendaProdutos {

	public static void main(String[] args) throws IOException, TasteException {
		
		File file = new File("dados.csv");
	
		DataModel dataModel = new FileDataModel(file);
		
		UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(dataModel);
	
		UserNeighborhood userNeighborhood = new ThresholdUserNeighborhood(0.1, userSimilarity, dataModel);
	
		UserBasedRecommender userBasedRecommender = new GenericUserBasedRecommender(dataModel, userNeighborhood, userSimilarity);
		
		List<RecommendedItem> recomendacoes = userBasedRecommender.recommend(2, 5);
		
		for(RecommendedItem recomendacao : recomendacoes){
			
			System.out.println(recomendacao);
		}
		
		
	}
}
