using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.EntityFrameworkCore;

namespace E_mart.Models;

[Table("users")]
[Index("Email", Name = "UK6dotkott2kjsp8vw4d0m25fb7", IsUnique = true)]
[Index("Phonenumber", Name = "UK70jmct5ej765l57mlcrdhxn1c", IsUnique = true)]
public partial class User
{
    [Key]
    [Column("userid")]
    public int Userid { get; set; }

    [Column("created_at")]
    [MaxLength(6)]
    public DateTime? CreatedAt { get; set; }

    [Column("email")]
    public string Email { get; set; } = null!;

    [Column("is_loyalty", TypeName = "bit(1)")]
    public ulong IsLoyalty { get; set; }

    [Column("password_hash")]
    [StringLength(255)]
    public string PasswordHash { get; set; } = null!;

    [Column("phonenumber")]
    [StringLength(15)]
    public string? Phonenumber { get; set; }

    [Column("supercoin")]
    public int Supercoin { get; set; }

    [Column("updated_at")]
    [MaxLength(6)]
    public DateTime? UpdatedAt { get; set; }

    [Column("username")]
    [StringLength(50)]
    public string Username { get; set; } = null!;

    [InverseProperty("User")]
    public virtual ICollection<Cart> Carts { get; set; } = new List<Cart>();

    [InverseProperty("User")]
    public virtual ICollection<Order> Orders { get; set; } = new List<Order>();
}
