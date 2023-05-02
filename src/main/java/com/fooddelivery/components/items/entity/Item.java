package com.fooddelivery.components.items.entity;


import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.*;

@NamedQueries({
		@NamedQuery(name = "item_findItemByCategory",
				query = "SELECT i FROM Item i WHERE i.category.id = :categoryId")
})
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "items")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {

	@Id
	@GeneratedValue
	private Integer id;


	private String name;
	private String description;

	@ManyToOne
	@JoinColumn(name = "categoryId",referencedColumnName = "id")
	private Category category ;

	private Double price;


	private String picture;




}
