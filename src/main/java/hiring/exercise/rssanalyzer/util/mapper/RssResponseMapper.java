package hiring.exercise.rssanalyzer.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import hiring.exercise.rssanalyzer.controller.response.RelatedNewsResponse;
import hiring.exercise.rssanalyzer.model.MatchedRss;

@Mapper
public interface RssResponseMapper {
  
  RssResponseMapper INSTANCE = Mappers.getMapper(RssResponseMapper.class);
  
  @Mapping(source = "data", target = "feeds")
  RelatedNewsResponse rssDataToRelatedNews(MatchedRss data);
  
}
