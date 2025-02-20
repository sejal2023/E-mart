using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.EntityFrameworkCore;

namespace E_mart.Models;

[Table("products")]
[Index("Categoryid", Name = "FK1krrsjgcawsfg8k8u4hm5gi8q")]
[Index("Subcategoryid", Name = "FKi5prgvmxtqxb5fji9wc2qivr2")]
public partial class Product
{
    [Key]
    [Column("productid")]
    public int Productid { get; set; }

    [Column("brand")]
    [StringLength(255)]
    public string? Brand { get; set; }

    [Column("created_at")]
    [MaxLength(6)]
    public DateTime? CreatedAt { get; set; }

    [Column("description")]
    [StringLength(255)]
    public string? Description { get; set; }

    [Column("description_en")]
    [StringLength(255)]
    public string? DescriptionEn { get; set; }

    [Column("description_fr")]
    [StringLength(255)]
    public string? DescriptionFr { get; set; }

    [Column("discount")]
    public double Discount { get; set; }

    [Column("image")]
    [StringLength(255)]
    public string? Image { get; set; }

    [Column("isdeal", TypeName = "bit(1)")]
    public ulong Isdeal { get; set; }

    [Column("price")]
    public double Price { get; set; }

    [Column("productname")]
    [StringLength(255)]
    public string Productname { get; set; } = null!;

    [Column("productname_en")]
    [StringLength(255)]
    public string? ProductnameEn { get; set; }

    [Column("productname_fr")]
    [StringLength(255)]
    public string? ProductnameFr { get; set; }

    [Column("rating")]
    public double Rating { get; set; }

    [Column("stocks")]
    public int Stocks { get; set; }

    [Column("updated_at")]
    [MaxLength(6)]
    public DateTime? UpdatedAt { get; set; }

    [Column("categoryid")]
    public int Categoryid { get; set; }

    [Column("subcategoryid")]
    public int? Subcategoryid { get; set; }

    [InverseProperty("Product")]
    public virtual ICollection<Cartitem> Cartitems { get; set; } = new List<Cartitem>();

    [InverseProperty("Product")]
    public virtual ICollection<Cart> Carts { get; set; } = new List<Cart>();

    [ForeignKey("Categoryid")]
    [InverseProperty("Products")]
    public virtual Category Category { get; set; } = null!;

    [ForeignKey("Subcategoryid")]
    [InverseProperty("Products")]
    public virtual Subcategory? Subcategory { get; set; }
}
