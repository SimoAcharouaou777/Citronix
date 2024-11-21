package com.youcode.citronix.mapper;

import com.youcode.citronix.dto.TreeDTO;
import com.youcode.citronix.entity.Tree;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TreeMapper {

    TreeMapper INSTANCE = Mappers.getMapper(TreeMapper.class);

    @Mappings({
            @Mapping(source = "field.id", target = "fieldId")
    })
    TreeDTO treeToTreeDTO(Tree tree);

    @Mappings({
            @Mapping(source = "fieldId", target = "field.id")
    })
    Tree treeDTOToTree(TreeDTO treeDTO);

    List<Tree> treeDTOsToTrees(List<TreeDTO> treeDTOs);
    List<TreeDTO> treesToTreeDTOs(List<Tree> trees);
}
