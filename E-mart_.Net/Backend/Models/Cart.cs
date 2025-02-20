using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json.Serialization;
using Microsoft.EntityFrameworkCore;

namespace E_mart.Models;

[Table("carts")]
[Index("Userid", Name = "FK7omdju5h5l95oek80ymvb56uq")]
[Index("Productid", Name = "FKf02ibgv71d0qx173h41rsjeri")]
public partial class Cart
{
    [Key]
    [Column("cartid")]
    public int Cartid { get; set; }

    [Column("created_at")]
    public DateTime? CreatedAt { get; set; } = DateTime.UtcNow; // Default value to prevent NULL error

    [Column("quantity")]
    public int Quantity { get; set; }

    [Column("updated_at")]
    public DateTime? UpdatedAt { get; set; } = DateTime.UtcNow;

    [Column("productid")]
    public int Productid { get; set; }

    [Column("userid")]
    public int Userid { get; set; }

    [JsonIgnore]
    [ForeignKey("Productid")]
    [InverseProperty("Carts")]
    public virtual Product? Product { get; set; }  // Made nullable

    [JsonIgnore]
    [ForeignKey("Userid")]
    [InverseProperty("Carts")]
    public virtual User? User { get; set; }  // Made nullable

    [InverseProperty("Cart")]
    public virtual ICollection<Cartitem> Cartitems { get; set; } = new List<Cartitem>();

    [InverseProperty("Cart")]
    public virtual ICollection<Order> Orders { get; set; } = new List<Order>();
}