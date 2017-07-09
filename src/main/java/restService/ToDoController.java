package restService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToDoController {

	private static final Logger logger = LoggerFactory.getLogger(ToDoController.class);

	// Map of ToDo list
	Map<Long, ToDo> toDoList = new HashMap<Long, ToDo>();

	// Display a list of ToDo items
	@RequestMapping(value = "/getToDoList", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllToDo() {
		logger.info("Get ToDoList");
		List<ToDo> toDos = new ArrayList<ToDo>();
		// using TreeSet for sorted toDo List
		TreeSet<Long> toDoKeys = new TreeSet<Long>(toDoList.keySet());
		for (Long i : toDoKeys) {
			toDos.add(toDoList.get(i));
		}
		return new ResponseEntity<Object>(toDos, HttpStatus.OK);
	}

	// Display a ToDo item for a given id
	@RequestMapping(value = "/getToDo/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getToDo(@PathVariable("id") long id) {
		ToDo temp = null;
		logger.info("Get ToDo with ID " + id);
		if (toDoList.containsKey(id)) {
			temp = toDoList.get(id);
			return new ResponseEntity<Object>(temp, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>("ToDo ID not found.", HttpStatus.NOT_FOUND);
		}
	}

	// Create new ToDo
	@RequestMapping(value = "/createToDo", method = RequestMethod.POST)
	public ResponseEntity<Object> createToDo(@RequestBody ToDo toDo) {
		if (toDo.getId() == null) {
			return new ResponseEntity<Object>("ToDo ID is absent.", HttpStatus.BAD_REQUEST);
		}

		logger.info("Create ToDo with ID " + toDo.getId());

		if (toDoList.containsKey(toDo.getId())) {
			return new ResponseEntity<Object>("ToDo ID already exists.", HttpStatus.BAD_REQUEST);
		}

		toDoList.put(toDo.getId(), toDo);
		return new ResponseEntity<Object>(toDo, HttpStatus.CREATED);
	}

	// Updating an existing ToDo item
	@RequestMapping(value = "/updateToDo/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<Object> updateToDo(@PathVariable("id") long id, @RequestBody ToDo toDo) {

		logger.info("Update ToDo with ID " + id);

		if (toDoList.containsKey(id)) {

			ToDo temp = toDoList.get(id);
			if (toDo.getTitle() != null) {
				temp.setTitle(toDo.getTitle());
			}
			if (toDo.getDescription() != null) {
				temp.setDescription(toDo.getDescription());
			}
			if (toDo.getDueDate() != null) {
				temp.setDueDate(toDo.getDueDate());
			}

			return new ResponseEntity<Object>(temp, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>("ToDo ID not found.", HttpStatus.NOT_FOUND);
		}
	}

}
