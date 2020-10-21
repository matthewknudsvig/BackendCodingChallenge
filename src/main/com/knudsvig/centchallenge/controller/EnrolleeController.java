package com.knudsvig.centchallenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.knudsvig.centchallenge.entity.Enrollee;
import com.knudsvig.centchallenge.service.EnrolleeService;

@RestController
//! APi which enables external use of CRUD operations on Enrollee objects in the database
public class EnrolleeController {
	
	@Autowired
	//! The Service which all operations are funneled through
	private EnrolleeService enService;
	
	@GetMapping(value = "/enrollee/get/{id}")
	/*
		\operation to get an Enrollee with the given id from the database via EnrolleeService
		\param id - The id of the Enrollee to get
		\return The Enrollee with the supplied id, or null if none exists
	 */
	public Enrollee getEnrollee(@PathVariable(value = "id") String id) {
		return enService.getEnrollee(id);
	}

	@PutMapping(value = "/enrollee/add")
	/*
		\operation to Add an Enrollee with the given values to the database via EnrolleeService
		\param enrollee - The Enrollee information to add, must have a name and birth date
		\return The Enrollee object added, or null if there was missing information
	 */
	public Enrollee addEnrollee(@RequestBody Enrollee enrollee) {
		return enService.addEnrollee(enrollee);
	}
	
	@PutMapping(value = "/enrollee/update")
	/*
		\operation to Update an existing Enrollee with the given values in the database via EnrolleeService
		\param enrollee - The Enrollee information to update with, including the id used to find the original
		\return True if the update was successful, false otherwise (such as no id, or non-existent id)
	 */
	public boolean updateEnrollee(@RequestBody Enrollee enrollee) {
		return enService.updateEnrollee(enrollee);
	}
	
	@DeleteMapping(value = "/enrollee/delete/{id}")
	/*
		\operation to Delete the Enrollee with the given id via EnrolleeService, including their Dependents
		\param id - The id of the Enrollee to delete
		\return True if an Enrollee of the given id existed, false otherwise
	 */
	public boolean deleteEnrollee(@PathVariable(value = "id") String id) {
		return enService.deleteEnrollee(id);
	}
	
	@PutMapping(value = "/enrollee/add/{enId}/dependent/{depId}")
	/*
		\operation to Add a Dependent to an Enrollee in the database via EnrolleeService
		\param enrollId - The id of the Enrollee to add the Dependent to
		\param depId - The id of the Dependent to add to the Enrollee
		\return True if the addition was successful, false otherwise
	 */
	public boolean addEnrolleeDependent(@PathVariable(value = "enrollId") String enrollId,
										@PathVariable(value = "depId") String depId) {
		return enService.addEnrolleeDependent(enrollId, depId);
	}
	
	@DeleteMapping(value = "/enrollee/delete/{enId}/dependent/{depId}")
	/*
		\operation to Remove a Dependent from an Enrollee in the database via EnrolleeService
		\param enrollId - The id of the Enrollee to remove the Dependent from
		\param depId - The id of the Dependent to remove from the Enrollee
		\return True if the rmoval was successful, false otherwise
	 */
	public boolean deleteEnrolleeDependent(@PathVariable(value = "enrollId") String enrollId,
											@PathVariable(value = "depId") String depId) {
		return enService.deleteEnrolleeDependent(enrollId, depId);
	}
}
