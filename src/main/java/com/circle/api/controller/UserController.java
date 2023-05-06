package com.circle.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.circle.api.model.Circle;
import com.circle.api.model.Survey;
import com.circle.api.model.User;
import com.circle.api.service.CircleService;
import com.circle.api.service.SurveyService;
import com.circle.api.facade.UserFacade;

@RestController
@EnableWebMvc
public class UserController {

  private UserFacade userFacade;
  private SurveyService surveyService;
  private CircleService circleService;
  private Logger logger;

  UserController(UserFacade userFacade, SurveyService surveyService, CircleService circleService) {
    this.userFacade = userFacade;
    this.surveyService = surveyService;
    this.circleService = circleService;
    this.logger = LoggerFactory.getLogger(UserController.class);
  }

  @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
  public UserResponse getUser(@PathVariable("id") String id) {
    logger.info("Getting USER#: " + id);

    UserResponse userResponse = userFacade.findById(id);

    logger.info("User: " + userResponse);

    return userResponse;
  }

  @RequestMapping(path = "/user", method = RequestMethod.POST)
  public UserResponse createUser(@RequestBody User user) {
    logger.info("Creating user...");

    return userFacade.createUser(user);
  }

  @RequestMapping(path = "/user/{id}/survey", method = RequestMethod.GET) 
  public List<UserSurveyResponse> findAllUserSurveys(@PathVariable("id") String id) {

    logger.info("Getting user surveys");

    return userFacade.findAllUserSurveys(id);

  }

  @RequestMapping(path = "/user/{uid}/survey/{sid}", method = RequestMethod.GET) 
  public UserSurveyResponse findUserSurvey(@PathVariable("uid") String userId, @PathVariable("sid") String surveyId) {

    logger.info("Getting USER#" + userId + " SURVEY#" + surveyId);

    return userFacade.findUserSurvey(userId,surveyId);

  }

  @RequestMapping(path = "/user/{id}/survey", method = RequestMethod.POST) 
  public UserSurveyResponse createUserSurvey(@PathVariable("id") String id, @RequestBody Survey survey) {

    logger.info("Creating SURVEY#" + survey.getSurveyFormId() + " response under USER#" + id);

    return userFacade.createUserSurvey(id,survey);

  }
  
  // Change if a user can be in multiple circles
  @RequestMapping(path = "/user/{uid}/circle/{cid}", method = RequestMethod.GET)
  public CircleMemberResponse getUserCircle(@PathVariable("uid") String userId, @PathVariable("cid") String circleId) {
    logger.info("Getting User Circle");

    CircleMemberResponse circleMemberResponse = userFacade.getUserCircle(userId,circleId);

    logger.info("User Circle Info: " + circleMemberResponse);

    return circleMemberResponse;
  }
}
// - /user/{id}          (GET - gets a user) 
// - /user               (POST - creates a new user)
// - /user/{id}/survey   (GET - gets a specified user's surveys)
// - /user/{id}/survey   (POST - add/create survey response for a user)
// - /user/{id}/circle   (GET - gets a user's circle members)
// - /user/{id}/circle   (POST - add/create user's circle members)
// - /user/{id}/circle   (PATCH - updates a user's circle members)
// - /user/{id}/survey/date=?queryParam (GET - get a survey at a specific date)

      