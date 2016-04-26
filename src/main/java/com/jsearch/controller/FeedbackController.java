package com.jsearch.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jsearch.bean.Feedback;

@Controller
public class FeedbackController {

	@RequestMapping(value = "feedback", method = RequestMethod.GET)
	public ModelAndView optIn() {
		return new ModelAndView("feedback", "command", new Feedback());
	}

	@RequestMapping(value = "feedbackConfirm", method = RequestMethod.POST)
	public String confirmOptIn(@ModelAttribute("SpringWeb") Feedback participant, ModelMap model) throws SQLException {
		participant.addFeedback();

		return "redirect:/index?thanks";
	}

	@RequestMapping("viewFeedback")
	public ModelAndView getFeedback() throws SQLException {
		return new ModelAndView("viewFeedback", "allFeedback", Feedback.getAllFeedback());
	}
}
