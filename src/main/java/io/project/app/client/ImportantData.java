/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.project.app.client;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author armena
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class ImportantData implements Serializable{
    
    private static final long serialVersionUID = 924651807079147434L;
    
    private String event;
    private String description;
    
}
