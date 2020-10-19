package com.knudsvig.centchallenge.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knudsvig.centchallenge.dao.DependentDao;
import com.knudsvig.centchallenge.entity.Dependent;

@Service
//! Service acting as a middleman between controller and dependent entity
public class DependentService {

	@Autowired
	//! The Data Access Object for dependent
	private DependentDao depDao;
	
	/*
		\operation to add a Dependent to the DependentDao if they aren't present in it
		\param dependent - dependent to be added
		\return the dependent generated in DependentDao
	 */
	public Dependent addDependent(Dependent dependent) {
		if((dependent.getName() != null) &&
				(dependent.getBirthDate() != null))
				return depDao.insert(dependent);
			return null;
	}
	
	/*
	\operation to update a dependent in the DependentDao, given a dependent object with a valid id
	\param dependent - dependent to be added
	\return True if the update was successful, false otherwise
 */
	public boolean updateDependent(Dependent dependent) {
		if((dependent.getId() != null) &&
			(dependent.getName() != null) &&
			(dependent.getBirthDate() != null) &&
			(depDao.existsById(dependent.getId())))
			return depDao.save(dependent) != null;
		return false;
	}

	/*
		\operation to delete a dependent in the DependentDao with given id
	  \param dependent - dependent to be deleted
		\return True if a dependent with the given id existed, false otherwise
 	*/
	public boolean deleteDependent(String id) {
		if(depDao.existsById(id)) {
			depDao.deleteById(id);
			return true;
		}
		return false;
	}
	
	/*
		\operation to get a Dependent from the DependentDao, or null if none exist
		\param id - the id of the desired Dependent
		\return The Dependent DependentDao, or null if none is present
	 */
	public Dependent getDependent(String id) {
		Optional<Dependent> dependent = depDao.findById(id);
		if(dependent.isPresent())
			return dependent.get();
		else
			return null;
	}
}
