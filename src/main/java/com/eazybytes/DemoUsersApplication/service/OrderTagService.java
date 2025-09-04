package com.eazybytes.DemoUsersApplication.service;

import com.eazybytes.DemoUsersApplication.model.Order;
import com.eazybytes.DemoUsersApplication.model.Tag;
import com.eazybytes.DemoUsersApplication.model.User;
import com.eazybytes.DemoUsersApplication.repository.OrderRepository;
import com.eazybytes.DemoUsersApplication.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderTagService {
    private final TagRepository tagRepository;
    private final OrderRepository orderRepository;

    public OrderTagService(TagRepository tagRepository, OrderRepository orderRepository){
        this.tagRepository=tagRepository;
        this.orderRepository=orderRepository;
    }
    public Order assignTagToOrder(Map<String, String> tag, Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id " + orderId));

        Tag updatedTag = tagRepository.findByTagName(tag.get("tagName"))
                .orElseThrow(() -> new RuntimeException("Tag not found with id " + tag.get("tagName")));

        order.getTags().add(updatedTag);
        return orderRepository.save(order);
    }
    public Order replaceTagToOrder(Map<String, String> tag, Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id " + orderId));

        Tag oldTag = tagRepository.findByTagName(tag.get("oldTag"))
                .orElseThrow(() -> new RuntimeException("Tag not found with id " + tag.get("oldTag")));
        Tag newTag = tagRepository.findByTagName(tag.get("newTag"))
                .orElseThrow(() -> new RuntimeException("Tag not found with id " + tag.get("newTag")));
        order.getTags().remove(oldTag);
        order.getTags().add(newTag);
        return orderRepository.save(order);
    }
    public List<Tag> getAllTagsForAnOrder(Long orderId){
        Order order = orderRepository.findById(orderId)
        .orElseThrow(() -> new RuntimeException("Tag not found with id " + orderId));
        return order.getTags();
    }
    public List<Order> getAllOrderForATag(Long tagId){
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new RuntimeException("Tag not found with id " + tagId));
        return tag.getOrders();
    }
}
