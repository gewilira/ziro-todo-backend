package com.ziro.todo.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "todo")
public class Todo extends Auditable<String> implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date completedDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date deleteDate;
	
	public boolean isCompleted() {
		return completedDate == null ? false : true;
	}
}
